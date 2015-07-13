package com.cgaf.model;

public class CtVariables {
	
	private Integer idVariable;
	private String descVariable;
	
	public CtVariables() {
		super();
	}

	public CtVariables(Integer idVariable, String descVariable) {
		super();
		this.idVariable = idVariable;
		this.descVariable = descVariable;
	}

	public Integer getIdVariable() {
		return idVariable;
	}

	public void setIdVariable(Integer idVariable) {
		this.idVariable = idVariable;
	}

	public String getDescVariable() {
		return descVariable;
	}

	public void setDescVariable(String descVariable) {
		this.descVariable = descVariable;
	}

}