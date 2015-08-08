package com.cgaf.vo;

import java.util.List;

public class DatosGenericosVo {

	private List<String> encabezados;
	private List<Object[]> rows;

	public List<String> getEncabezados() {
		return encabezados;
	}
	public void setEncabezados(List<String> encabezados) {
		this.encabezados = encabezados;
	}
	public List<Object[]> getRows() {
		return rows;
	}
	public void setRows(List<Object[]> rows) {
		this.rows = rows;
	}

}
