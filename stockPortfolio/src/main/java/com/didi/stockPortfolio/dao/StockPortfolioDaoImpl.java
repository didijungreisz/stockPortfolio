package com.didi.stockPortfolio.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.validation.Valid;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import com.didi.stockPortfolio.entity.Stock;
import com.didi.stockPortfolio.entity.StockState;
import com.didi.stockPortfolio.entity.Trader;
import com.didi.stockPortfolio.rest.BadArgumentException;
import com.didi.stockPortfolio.rest.TraderNotFoundException;

@Repository
public class StockPortfolioDaoImpl implements StockPortfolioDAO {
		@Autowired
		private SessionFactory sessionFactory;
		private static final int daysAgo = -475;
		
		@Override
		public int addNewTrader(@Valid List<Stock> stockPortfolioList) {	
			Session currentSession = sessionFactory.getCurrentSession();
	        Trader newTrader = new Trader();
	        currentSession.save(newTrader);   
	        stockPortfolioList.forEach(stock -> stock.setTraderId(newTrader.getId()));
	        newTrader.setStocksList(stockPortfolioList);
			return newTrader.getId();
		}
		
		@Override
		public void replaceTraderPortfolio(Trader theTrader, List<Stock> newStocksList) {
			theTrader.getStocksList().clear();
			newStocksList.forEach(stock -> stock.setTraderId(theTrader.getId()));
			theTrader.getStocksList().addAll(newStocksList);
		}
		
		@Override
		public double getPortfolioValue(int traderId) {
			Trader theTrader = getTrader(traderId);
			//get the stocks list with up to date values 
			List<StockState> stocksUpToDateList = getMarketStocksList();		
			List<Stock> portfolio = theTrader.getStocksList();
			HashMap<String, Double> stockValueMap = new HashMap<String, Double>();	
			
			// put all the stocks in hash map to find easily each stock and his value 
			for (StockState stockState : stocksUpToDateList) {
				stockValueMap.put(stockState.getStockName(), stockState.getStockValue());	
			}		
			double portfolioValue = 0.00;
			for (Stock stock : portfolio) {
				portfolioValue += stock.getStockAmount() * stockValueMap.get(stock.getstockName());
			}	
			return portfolioValue;	
		}
		
		@Override
		public void updateTraderPortfolio(Trader theTrader , List<Stock> stocksToUpdate) {
			HashMap<String, Stock> traderStockportfolio = new HashMap<>();
			theTrader.getStocksList().forEach(stock-> traderStockportfolio.put(stock.getstockName(), stock));
			for (Stock stock : stocksToUpdate) {
				double amount = traderStockportfolio.get(stock.getstockName()).getStockAmount() + stock.getStockAmount();
				if(amount < 0) {
					throw new BadArgumentException("Invalid amount of " + stock.getstockName()+ 
							" sold stocks! you can not sell more then " + traderStockportfolio.get(stock.getstockName()).getStockAmount());
				}
				else if ( amount == 0) {
					traderStockportfolio.remove(stock.getstockName());
				}
				else {
				traderStockportfolio.get(stock.getstockName()).setStockAmount(amount);
				}
			}
			List<Stock> updatedList = new ArrayList<Stock>(traderStockportfolio.values());
			replaceTraderPortfolio(theTrader, updatedList);
		}


	@Override
	public List<StockState> getMarketStocksList() {
		Session currentSession = sessionFactory.getCurrentSession();	
		String hql = "select new StockState(stockName, stockValue, max(date)) from StockState group by stockName";
		Query<StockState> theQuery = currentSession.createQuery(hql, StockState.class);
		// execute query and get result list
		List<StockState> stocks = theQuery.getResultList();	
		return stocks;
	}
	
	public Trader getTrader(int traderId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Trader trader = currentSession.get(Trader.class, traderId);
		if (trader == null) {
			throw new TraderNotFoundException("Trader id not found - " + traderId);
		}
		return trader;
	}

	@Override
	public StockState getStockState(String stockName) {
		return null;	
	}
	
	private String getDateAsString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, daysAgo);
		return dateFormat.format(cal.getTime());	
	}

	@Override
	public HashSet<String> getAvailabelStocks(){
		Session currentSession = sessionFactory.getCurrentSession();
		String hql = "select stockName from StockState group by stockName";
		Query<String> query = currentSession.createQuery(hql);
		HashSet<String> stocks = new HashSet<String>(query.getResultList());	
		return stocks;
	}

	@Override
	public List<Stock> getMyStocksList(int traderId) {
		//TODO: make changes to get a better list 
		Trader theTrader = getTrader(traderId);
		return theTrader.getStocksList();
	}

	@Override
	public String recommendationByPerformance(int traderId) {
		String flagDate = getDateAsString();
		Trader theTrader = getTrader(traderId);
		List<String> traderStocks = new ArrayList<>();
		theTrader.getStocksList().forEach(stock-> traderStocks.add(stock.getstockName()));
		Session currentSession = sessionFactory.getCurrentSession();
		String hql = "select new StockState( A.stockName,(A.stockValue - B.stockValue), max(A.date))" + 
				"from StockState A, StockState B " + 
				" where A.stockName in (:traderStocks) "+
				"and A.date > :day " + 
				"and B.date <= :day " + 
				"and A.stockName = B.stockName " + 
				"group by B.stockName "+
				"order by (A.stockValue - B.stockValue) desc ";
		Query<StockState> query = currentSession.createQuery(hql);
		query.setMaxResults(1);
		query.setParameterList("traderStocks", traderStocks);
		query.setParameter("day",flagDate);
		StockState recommendedStock = query.getSingleResult();	
		return "The most recommended stock by performance is: " + recommendedStock.getStockName();	
	}

	@Override
	public String recommendationByMostStable(int traderId) {
		String flagDate = getDateAsString();
		Trader theTrader = getTrader(traderId);
		List<String> traderStocks = new ArrayList<>();
		theTrader.getStocksList().forEach(stock-> traderStocks.add(stock.getstockName()));
		Session currentSession = sessionFactory.getCurrentSession();
		String hql = "select new StockState (A.stockName, (max(A.stockValue) - min(A.stockValue)), max(B.date)) "
				+ "from StockState A, StockState B "
				+ "where A.stockName in (:traderStocks) "
				+ "and B.date <= :dayFrom "
				+ "and A.date >= B.date "
				+ "group by A.stockName "
				+ "order by (max(A.stockValue) - min(A.stockValue)) asc";
		Query<StockState> query = currentSession.createQuery(hql);
		query.setMaxResults(1);
		query.setParameterList("traderStocks", traderStocks);
		query.setParameter("dayFrom",flagDate);
		StockState recommendedStock = query.getSingleResult();		
		return "The most stable stock is: " + recommendedStock.getStockName();
	}

	@Override
	public String recommendationByBest(int traderId) {
		Trader theTrader = getTrader(traderId);
		List<String> traderStocks = new ArrayList<>();
		theTrader.getStocksList().forEach(stock-> traderStocks.add(stock.getstockName()));
		Session currentSession = sessionFactory.getCurrentSession();	
		String hql = "select new StockState(stockName, stockValue, max(date)) "
				+ "from StockState "
				+ "where stockName not in (:traderStocks) "
				+ "group by stockName "
				+ "order by stockValue desc";
		Query<StockState> query = currentSession.createQuery(hql, StockState.class);
		query.setParameterList("traderStocks", traderStocks);
		query.setMaxResults(1);
		StockState recommendedStock = query.getSingleResult();		
		return recommendedStock.getStockName();		
	}

	




}
