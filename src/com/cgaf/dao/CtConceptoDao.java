package com.cgaf.dao;

import java.util.List;

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

}
