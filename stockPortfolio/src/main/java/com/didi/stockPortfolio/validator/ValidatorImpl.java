package com.didi.stockPortfolio.validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.didi.stockPortfolio.entity.Stock;
import com.didi.stockPortfolio.entity.Trader;
import com.didi.stockPortfolio.rest.BadArgumentException;
import com.didi.stockPortfolio.rest.TraderNotFoundException;
import com.didi.stockPortfolio.service.StockPortfolioService;

@Component
public class ValidatorImpl implements Validator {
	
	@Autowired
	private StockPortfolioService stockPortfolioService;
	
	@Override
	public void checkNewTrader(@Valid List<Stock> stockPortfolioList) {
		for (Stock stock : stockPortfolioList) {
			//check if the stock is in the available stocks list
			stockNameIsSupportedValidation(stock);		
			//check if the amount is bigger then 0 
			amountOfStockValidation(stock);
			}			
			//check if there is double items
			isAllStocksAreUniqueValidation(stockPortfolioList);
	}

	@Override
	public void replaceTraderPortfolioCheck(Trader theTrader, List<Stock> newStocksList) {
		for (Stock stock : newStocksList) {
			//check if the stock is in the available stocks list
			stockNameIsSupportedValidation(stock);		
			//check if the amount is bigger then 0 
			amountOfStockValidation(stock);
			}			
			//check if there is double items
			isAllStocksAreUniqueValidation(newStocksList);
	}

	@Override
	public void updateTraderPortfolioCheck(Trader theTrader, List<Stock> updatedStockList) {
		System.out.println("im not should be here");
		HashSet<String> traderOwendStocks = new HashSet<>();
		theTrader.getStocksList().forEach(stock-> traderOwendStocks.add(stock.getstockName()));		
		for (Stock stock : updatedStockList) {	
			//check if the stock is in the available stocks list
			stockNameIsSupportedValidation(stock);
			if(!traderOwendStocks.contains(stock.getstockName())) {
				throw new BadArgumentException("The stock " + stock.getstockName()+ " is not in your portfolio");
			}
		}		
		
			//check if there is double items
			isAllStocksAreUniqueValidation(updatedStockList);
		
	}

	private void amountOfStockValidation (Stock stock) {
		if (stock.getStockAmount() <= 0) {
			throw new BadArgumentException("Invalid amount. amount should be greater then 0. "
					+ "please fix the amount of: " + stock.getstockName());
		}
	}
	
	private void stockNameIsSupportedValidation (Stock stock) {
		
		//TODO : first check if it valid name
		namePatternValidation(stock);
		
		HashSet<String> availableStocks = stockPortfolioService.getAvailabelStocks();
		if(! availableStocks.contains(stock.getstockName())) {
			throw new BadArgumentException("The stock " + stock.getstockName() + " is not supported");
		}
	}
	
	private void namePatternValidation(Stock stock) {
		if(!stock.getstockName().matches("^[A-Z]*$")){
            throw new BadArgumentException("Invalid stock name '" +stock.getstockName()+ "'");
        }		
	}

	private void isAllStocksAreUniqueValidation(@Valid List<Stock> stockPortfolioList) {
		HashSet<String> listValidation = new HashSet<>();
		for (Stock stock : stockPortfolioList) {
			if(listValidation.contains(stock.getstockName())){
				throw new BadArgumentException("Your list contains duplicates of " + stock.getstockName());
			}
			listValidation.add(stock.getstockName());		
		}
	}

}
