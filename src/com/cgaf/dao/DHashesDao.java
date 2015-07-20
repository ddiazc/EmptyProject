package com.cgaf.dao;

import com.cgaf.model.DHashes;

public interface DHashesDao {
	
	/**
	 * Metodo que obtiene el hash asociado al usuario.
	 * 
	 * @param idUsuario Id asociado al usuario.
	 * @return Devuelve objeto de tipo DHashes.
	 * @throws Exception
	 */
	DHashes getUserHash(Integer idUsuario) throws Exception;

}
