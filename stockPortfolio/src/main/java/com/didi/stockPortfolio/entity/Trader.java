package com.didi.stockPortfolio.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="traders")
public class Trader {
	
	@Override
	public String toString() {
		return "Trader [id=" + id + ", stocksList=" + stocksList + "]";
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TRADER_ID")
	private int id;
	

	    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
	    @JoinColumn(name = "TRADER_ID")
	    private List<Stock> stocksList;


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public List<Stock> getStocksList() {
			return stocksList;
		}


		public void setStocksList(List<Stock> stocksList) {
			this.stocksList = stocksList;
		}
	    
	    

}
