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

}
