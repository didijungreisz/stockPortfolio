package com.didi.stockPortfolio.rest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StockPortfolioExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<StockPortfolioExceptionResponse> handleException(TraderNotFoundException exc) {
		StockPortfolioExceptionResponse error = new StockPortfolioExceptionResponse(
											HttpStatus.NOT_FOUND.value(),
											exc.getMessage(),
											System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<StockPortfolioExceptionResponse> handleException(BadArgumentException exc) {
		StockPortfolioExceptionResponse error = new StockPortfolioExceptionResponse(
											HttpStatus.NOT_ACCEPTABLE.value(),
											exc.getMessage(),
											System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}
	

	@ExceptionHandler
	public ResponseEntity<StockPortfolioExceptionResponse> handleException(Exception exc) {
		StockPortfolioExceptionResponse error = new StockPortfolioExceptionResponse(
											HttpStatus.BAD_REQUEST.value(),
											exc.getMessage(),
											System.currentTimeMillis());
				
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
}





