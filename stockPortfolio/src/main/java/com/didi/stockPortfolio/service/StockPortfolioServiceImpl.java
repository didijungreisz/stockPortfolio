package com.didi.stockPortfolio.service;

import com.didi.stockPortfolio.dao.StockPortfolioDAO;
import com.didi.stockPortfolio.entity.Stock;
import com.didi.stockPortfolio.entity.StockState;
import com.didi.stockPortfolio.entity.Trader;
import com.didi.stockPortfolio.validator.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockPortfolioServiceImpl implements StockPortfolioService {

	@Autowired
	private StockPortfolioDAO stockPortfolioDAO;
	
	@Autowired
	private Validator validator;
	
	@Transactional
	@Override
	public int addNewTrader(@Valid List<Stock> stockPortfolioList) {
			validator.checkNewTrader(stockPortfolioList);
		return stockPortfolioDAO.addNewTrader(stockPortfolioList);
	}

	@Transactional
	@Override
	public double getPortfolioValue(int clientId) {
		return stockPortfolioDAO.getPortfolioValue(clientId);
	}
	
	@Transactional
	@Override
	public void replaceTraderPortfolio(int traderId, List<Stock> newStocksList) {
		Trader theTrader = stockPortfolioDAO.getTrader(traderId);
		validator.replaceTraderPortfolioCheck(theTrader, newStocksList);
		stockPortfolioDAO.replaceTraderPortfolio(theTrader, newStocksList);	
	}
	
	@Transactional
	@Override
	public void updateTraderPortfolio(int traderId, List<Stock> updatedStockList) {
		Trader theTrader = stockPortfolioDAO.getTrader(traderId);
		validator.updateTraderPortfolioCheck(theTrader, updatedStockList);
		stockPortfolioDAO.updateTraderPortfolio(theTrader, updatedStockList);
		
	}
	
	//no needed
	@Transactional
	@Override
	public StockState getStockState(String stockName) {
		return stockPortfolioDAO.getStockState(stockName);
		
	}

	
	@Transactional
	@Override
	public List<StockState> getMarketStocksList() {
		return stockPortfolioDAO.getMarketStocksList();
	}
	
	@Transactional
	@Override
	public HashSet<String> getAvailabelStocks() {
		return stockPortfolioDAO.getAvailabelStocks();
	}
	
	@Transactional
	@Override
	public List<Stock> getMyStocksList(int traderId) {
		return stockPortfolioDAO.getMyStocksList(traderId);
	}
	
	@Transactional
	@Override
	public String recommendationByPerformance(int traderId) {
		return stockPortfolioDAO.recommendationByPerformance(traderId);
	}
	
	@Transactional
	@Override
	public String recommendationByMostStable(int traderId) {
		return stockPortfolioDAO.recommendationByMostStable(traderId);
	}

	@Transactional
	@Override
	public String recommendationByBest(int traderId) {
		return stockPortfolioDAO.recommendationByBest(traderId);

	}
	
	

}
