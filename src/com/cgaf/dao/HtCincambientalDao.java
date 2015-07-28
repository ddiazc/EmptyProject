package com.cgaf.dao;

import java.util.List;

import com.cgaf.model.HtCincambiental;

public interface HtCincambientalDao {
	
	/**
	 * Metodo que obtiene todos los registros de la tabla HT_CINCAMBIENTAL.
	 * 
	 * @return Devuelve una lista de todos los registros.
	 * @throws Exception
	 * 
	 */
	List<HtCincambiental> getRegistros() throws Exception;

}
