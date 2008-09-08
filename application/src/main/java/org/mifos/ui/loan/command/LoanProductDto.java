package org.mifos.ui.loan.command;

public class LoanProductDto {
	
	private String longName;
	private String shortName;
	private Double minInterestRate;
	private Double maxInterestRate;

	
	public String getLongName() {
		return longName;
	}


	public void setLongName(String longName) {
		this.longName = longName;
	}


	public String getShortName() {
		return shortName;
	}


	public void setShortName(String shortName) {
		this.shortName = shortName;
	}


	public Double getMinInterestRate() {
		return minInterestRate;
	}


	public void setMinInterestRate(Double minInterestRate) {
		this.minInterestRate = minInterestRate;
	}


	public Double getMaxInterestRate() {
		return maxInterestRate;
	}


	public void setMaxInterestRate(Double maxInterestRate) {
		this.maxInterestRate = maxInterestRate;
	}


	public LoanProductDto() {
		longName = "";
		shortName = "";
		minInterestRate = 0.0;
		maxInterestRate = 0.0;
	}

}
