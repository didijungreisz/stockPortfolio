package com.didi.stockPortfolio.entity;

import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Entity
@Table(name = "STOCKS")
public class Stock {

    //default constructor
    public Stock(){}

    public Stock(String stockName){
        this.stockName = stockName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="STOCK_ID")
    private long stockId;

    @NotNull
    @Size(min=2, max=10)
    @Pattern(regexp="^[A-Z]*$")
    @Column(name="STOCK_NAME")
    private String stockName;

    @NotNull
    @Min(0)
    @Column(name="STOCK_AMOUNT")
    private double stockAmount;

    @Column(name="TRADER_ID")
    private long traderId;


    @Override
    public boolean equals(Object updatedStock) {
    	 if (!(updatedStock instanceof Stock)) {
             return false;
         }

        Stock stock = (Stock)updatedStock;
        return(stock.getstockName().equals(this.getstockName()));
    }


    public long getStockId()
    {
        return stockId;
    }

    public void setStockId(long stockId)
    {
        this.stockId = stockId;
    }

    public String getstockName()
    {
        return stockName;
    }

    public void setstockName(String stockSymbol)
    {
        this.stockName = stockSymbol;
    }

    public double getStockAmount()
    {
        return stockAmount;
    }

    public void setStockAmount(double amount)
    {
        this.stockAmount = amount;
    }

    public long getTraderId()
    {
        return traderId;
    }

    public void setTraderId(int traderId)
    {
        this.traderId = traderId;
    }
}

