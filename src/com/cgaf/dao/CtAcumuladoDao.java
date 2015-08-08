package com.cgaf.dao;

import java.util.List;

import com.cgaf.model.CtAcumulado;

public interface CtAcumuladoDao {

	List<CtAcumulado> findAllCtAcumulados() throws Exception;
}
