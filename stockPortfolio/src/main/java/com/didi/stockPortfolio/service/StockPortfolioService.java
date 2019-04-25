package com.didi.stockPortfolio.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.didi.stockPortfolio.entity.Stock;
import com.didi.stockPortfolio.entity.StockState;

public interface StockPortfolioService {
	
	public StockState getStockState(String stockName);

	public List<StockState> getMarketStocksList();

	public int addNewTrader(List<Stock> stockPortfolioList);

	public double getPortfolioValue(int clientId);

	public void replaceTraderPortfolio(int traderId, List<Stock> newStocksList);

	public void updateTraderPortfolio(int traderId,  List<Stock> updatedStockList);

	public HashSet<String> getAvailabelStocks();

	public List<Stock> getMyStocksList(int traderId);

	public String recommendationByPerformance(int traderId);

	public String recommendationByMostStable(int traderId);

	public String recommendationByBest(int traderId);

}
