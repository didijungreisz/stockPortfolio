package com.didi.stockPortfolio.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "STOCK_HISTORY")
public class StockState {
	
	@Id
	@NotNull
    @Size(min=2, max=10)
    @Pattern(regexp="^[A-Z]*$")
    @Column(name="STOCK_NAME")
    private String stockName;

    @NotNull
    @Min(0)
    @Column(name="STOCK_VALUE")
    private double stockValue;

    @Column(name="DATE_OF_VALUE")
    private String date;

	public StockState(@NotNull @Size(min = 2, max = 10) @Pattern(regexp = "^[A-Z]*$") String stockName,
			@NotNull @Min(0) double stockValue, String date) {
		super();
		this.stockName = stockName;
		this.stockValue = stockValue;
		this.date = date;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getStockValue() {
		return stockValue;
	}

	public void setStockValue(double stockValue) {
		this.stockValue = stockValue;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
