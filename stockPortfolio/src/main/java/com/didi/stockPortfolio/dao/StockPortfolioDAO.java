package com.didi.stockPortfolio.dao;

import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import com.didi.stockPortfolio.entity.Stock;
import com.didi.stockPortfolio.entity.StockState;
import com.didi.stockPortfolio.entity.Trader;

public interface StockPortfolioDAO {
		
	public StockState getStockState(String stockName);

	public List<StockState> getMarketStocksList();

	public int addNewTrader(@Valid List<Stock> stockPortfolioList);

	public double getPortfolioValue(int clientId);

	public void replaceTraderPortfolio(Trader theTrader, List<Stock> newStocksList);

	public void updateTraderPortfolio(Trader theTrader, List<Stock> updatedStockList);

	public HashSet<String> getAvailabelStocks();
	
	public Trader getTrader(int traderId);

	public List<Stock> getMyStocksList(int traderId);

	public String recommendationByPerformance(int traderId);

	public String recommendationByMostStable(int traderId);

	public String recommendationByBest(int traderId);


}
