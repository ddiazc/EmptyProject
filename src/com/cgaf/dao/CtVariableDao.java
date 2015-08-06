package com.cgaf.dao;

import java.util.List;

import com.cgaf.model.CtVariable;

public interface CtVariableDao {
	
	/**
	 * Metodo que obtiene todos los registros de la tabla CT_VARIABLE.
	 * 
	 * @return Devuelve una lista que contiene todos los registros de la tabla CT_VARIABLE.
	 * @throws Exception
	 * 
	 */
	List<CtVariable> getVariables() throws Exception;
	
	/**
	 * Metodo que obtiene los registros de la tabla CT_VARIABLE segun los
	 * tipos de variable que fueron seleccionados.
	 * 
	 * @param tipos Lista de tipos de variable seleccionados.
	 * @return Devuelve una lista que contiene los registros deseados de la tabla CT_VARIABLE.
	 * @throws Exception
	 * 
	 */
	List<CtVariable> getVariables(List<Integer> tipos) throws Exception;
 
}
