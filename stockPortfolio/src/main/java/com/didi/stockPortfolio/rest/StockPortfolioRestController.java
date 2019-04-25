package com.didi.stockPortfolio.rest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.didi.stockPortfolio.entity.Stock;
import com.didi.stockPortfolio.entity.StockState;
import com.didi.stockPortfolio.service.StockPortfolioService;


@RestController
@RequestMapping("/api")
public class StockPortfolioRestController {

	// auto wire the StockPortfolioService
	@Autowired
	private StockPortfolioService stockPortfolioService;
	
	// add mapping for POST /addNewTrader  - add new trader stock portfolio
	@PostMapping("/addNewTrader")
	public int createNewPortfolio(@Valid @RequestBody List<Stock> stockPortfolioList){
        return stockPortfolioService.addNewTrader(stockPortfolioList);
	   }
	
	 @GetMapping("/trader/value/{traderId}")
	    public double traderPortfolioValue(@PathVariable int traderId){
	        return stockPortfolioService.getPortfolioValue(traderId);
	    }
	 
	  @PutMapping("/trader/replace/{traderId}")
	    public void replacePortfolio(@Valid @RequestBody List<Stock> newStocksList, @PathVariable int traderId){
	        stockPortfolioService.replaceTraderPortfolio(traderId ,newStocksList);
	    }
	   
	    @PutMapping("/trader/update/{traderId}")
	    public void updatePortfolio(@Valid @RequestBody List<Stock> stocksToUpdate, @PathVariable int traderId){
	    	stockPortfolioService.updateTraderPortfolio(traderId, stocksToUpdate);
	    }
	    
	    @GetMapping("/trader/myPortfolio/{traderId}")
		public List<Stock> getMyStocksList(@PathVariable int traderId) {	
			return stockPortfolioService.getMyStocksList(traderId);
		}
	
	    @GetMapping("/recommendations/performance/{traderId}")
	    public String getMostPerformingStock(@PathVariable int traderId){
	        return stockPortfolioService.recommendationByPerformance(traderId);
	    }
	 
	    @GetMapping("/recommendations/stable/{traderId}")
	    public String getMostStableStock(@PathVariable int traderId){
	        return stockPortfolioService.recommendationByMostStable(traderId);
	    }
	    
	    @GetMapping("/recommendations/best/{traderId}")
	    public String getBestStock(@PathVariable int traderId){
	        return stockPortfolioService.recommendationByBest(traderId);
	    }
	    
	    
	    

	 
	// add mapping for GET /customers
	@GetMapping("/stocksList")
	public List<StockState> getMarketStocksList() {	
		return stockPortfolioService.getMarketStocksList();
	}
	
	
	
	
	@GetMapping("/stocks/{stockName}")
	public StockState getCustomer(@PathVariable String stockName) {	
		StockState theStock = stockPortfolioService.getStockState(stockName);	
		return theStock;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
