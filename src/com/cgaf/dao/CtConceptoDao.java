package com.cgaf.dao;

import java.util.List;

import com.cgaf.model.CtConcepto;

public interface CtConceptoDao {
	
	/**
	 * Metodo que obtiene los IDs de las variables relacionadas a la
	 * tabla proporcionada en el argumento.
	 *  
	 * @param idTabla Identificador de la tabla en la base de datos.
	 * @return Devuelve un arreglo de IDs.
	 * @throws Exception
	 * 
	 */
	List<String> getIdVariables(Integer idTabla) throws Exception;
	
	/**
	 * Metodo que obtiene registros de la tabla, segun los IDs de variables
	 * proporcionados en el parametro.
	 * 
	 * @param variables Lista de IDs de variables del cual se desea conocer
	 * la tabla en la que se encuentran.
	 * @return Devuelve una lista de los registros.
	 * @throws Exception
	 * 
	 */
	List<CtConcepto> getRegistros(List<Integer> variables) throws Exception;

}
