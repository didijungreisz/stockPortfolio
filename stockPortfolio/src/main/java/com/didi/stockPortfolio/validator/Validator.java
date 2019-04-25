package com.didi.stockPortfolio.validator;

import java.util.List;

import javax.validation.Valid;

import com.didi.stockPortfolio.entity.Stock;
import com.didi.stockPortfolio.entity.Trader;

public interface Validator {

	void checkNewTrader(@Valid List<Stock> stockPortfolioList);

	void replaceTraderPortfolioCheck(Trader theTrader, List<Stock> newStocksList);

	void updateTraderPortfolioCheck(Trader theTrader, List<Stock> updatedStockList);

}