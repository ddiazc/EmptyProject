package com.cgaf.model;

public class CtTipos {
	
	private Integer idTipo;
	private String descTipo;
	
	public CtTipos() {
		super();
	}

	public CtTipos(Integer idTipo, String descTipo) {
		super();
		this.idTipo = idTipo;
		this.descTipo = descTipo;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescTipo() {
		return descTipo;
	}

	public void setDescTipo(String descTipo) {
		this.descTipo = descTipo;
	}

}
