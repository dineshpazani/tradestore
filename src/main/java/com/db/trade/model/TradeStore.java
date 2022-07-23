package com.db.trade.model;

import java.util.Date;

public class TradeStore {

	private String tradeId;
	private Integer version;
	private String countryPartyId;
	private String bookId;
	private Date maturityDate;
	private Date creationDate;
	private String expired;
	
	

	public TradeStore(String tradeId, Integer version, String countryPartyId, String bookId, Date maturityDate,
			Date creationDate, String expired) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.countryPartyId = countryPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.creationDate = creationDate;
		this.expired = expired;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCountryPartyId() {
		return countryPartyId;
	}

	public void setCountryPartyId(String countryPartyId) {
		this.countryPartyId = countryPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

}
