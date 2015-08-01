package com.cgaf.dao;

import java.sql.Timestamp;
import java.util.List;

import com.cgaf.model.CtConcepto;
import com.cgaf.model.HtGeneric;

public interface HqlUtilDao {
	
	/**
	 * Metodo que obtiene la informacion relacionada a la variable
	 * pasada como parametro en el periodo de tiempo seleccionado.
	 * 
	 * @param object Objeto de tipo CtConcepto que contiene el ID de la variable
	 * y el nombre de la tabla donde se debe de buscar.
	 * @param fechaIni Inicio del intervalo de tiempo.
	 * @param fechaFin Final del intervalo de tiempo.
	 * @return Devuelve una lista de tipo HtGeneric que contiene la informacion
	 * para el reporte.
	 * 
	 */
	List<HtGeneric> executeQuery(CtConcepto object, Timestamp fechaIni, Timestamp fechaFin);

}