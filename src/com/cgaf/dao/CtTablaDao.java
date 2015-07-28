package com.cgaf.dao;


public interface CtTablaDao {
	
	/**
	 * Metodo que obtiene el ID de la tabla pasada como parametro.
	 * 
	 * @param tableName Nombre de la tabla de la cual se quiere saber el ID.
	 * @return Devuelve un objeto de tipo Integer. 
	 * @throws Exception
	 * 
	 */
	Integer getIdTabla(String tableName) throws Exception;

}
