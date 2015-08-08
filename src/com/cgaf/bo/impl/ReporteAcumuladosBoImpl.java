package com.cgaf.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cgaf.bo.ReporteAcumuladosBo;
import com.cgaf.dao.CtAcumuladoDao;
import com.cgaf.dao.CtTipoAcumuladoDao;
import com.cgaf.dao.HqlUtilDao;
import com.cgaf.model.CtAcumulado;
import com.cgaf.model.CtConcepto;
import com.cgaf.model.CtTipoAcumulado;
import com.cgaf.model.CtVariable;
import com.cgaf.util.FuncionesUtil;
import com.cgaf.vo.DatosGenericosVo;

public class ReporteAcumuladosBoImpl implements ReporteAcumuladosBo {

	private CtTipoAcumuladoDao ctTipoAcumuladoDao;
	private CtAcumuladoDao ctAcumuladoDao;
	private HqlUtilDao hqlUtilDao;
	
	private static final Logger log = Logger.getLogger(ReporteAcumuladosBoImpl.class);

	@Override
	public List<CtAcumulado> findAllCtAcumulado() throws Exception {
		return ctAcumuladoDao.findAllCtAcumulados();
	}

	@Override
	public List<CtTipoAcumulado> findAllCtTipoAcumulado() throws Exception {
		return ctTipoAcumuladoDao.findAllCtTipoAcumulados();
	}

	@Override
	public List<Integer> getAcumuladosByTipoAcum(List<Integer> tipoAcum, List<CtAcumulado> list1) throws Exception {
		List<Integer> result = new ArrayList<>();
		try {
			for (CtAcumulado ctAcumulado : list1) {
				if (tipoAcum.contains(ctAcumulado.getCtTipoAcumulado().getIdTipoAcumulado())) {
					result.add(ctAcumulado.getIdAcumulado());
				}
			}
		} catch (Exception e) {
			log.error("Error al obtener claves de acumulados por tipos de acumulados", e);
		}

		return result;
	}
	
	@Override
	public List<String> getVariablesByTipom(List<String> tipo, List<CtVariable> list1) throws Exception {
		List<String> resutl = new ArrayList<>();
		try {
			for (CtVariable ctVariable : list1) {
				if (ctVariable.getCtTipoVariable() != null) {
					if (tipo.contains(ctVariable.getCtTipoVariable().getIdTipoVariable().toString())  ) {
						resutl.add(ctVariable.getIdVariable().toString());
					}
				}
			}
		} catch (Exception e) {
			log.error("Error al obtener claves de variables por tipos", e);
		}
		return resutl;
	}

	@Override
	public DatosGenericosVo getReporteAcumulado(List<CtConcepto> listConceptos, CtAcumulado acumulado, Date fechaInicio,
			Date fechaFin) throws Exception {
		
		List<Map<String, Object>> rows = hqlUtilDao.getReporteAcumulado(listConceptos, acumulado, fechaInicio, fechaFin);
		DatosGenericosVo genericosVo = new DatosGenericosVo();
		
		int rowSize = rows.size();
		int colSize = rows.get(0).size();
		List<String> vals = new ArrayList<>(rows.get(0).keySet());
		
		List<Object[]> list = new ArrayList<>();
		Object[] array;

		for (int i = 0; i < rowSize; i++) {
			Map<String, Object> row = rows.get(i);
			array = new Object[colSize];

			for (int j = 0; j < colSize; j++) {
				array[j] = row.get(vals.get(j));
			}
			list.add(array);
		}
		
		genericosVo.setEncabezados(FuncionesUtil.getEncabezados(listConceptos, vals));
		genericosVo.setRows(list);
		return genericosVo;
	}
	
	@Override
	public List<CtAcumulado> getCtAcumuladosByIds(List<Integer> ids, List<CtAcumulado> catalogo) throws Exception {
		List<CtAcumulado> list = new ArrayList<>();
		try {
			for (CtAcumulado ctAcumulado : catalogo) {
				if (ids.contains(ctAcumulado.getIdAcumulado())) {
					list.add(ctAcumulado);
				}
			}
		} catch (Exception e) {
			log.error("Error al obtener objetos de ctAcumulado por ids",e);
		}
		
		return list;
	}
	
	public void setCtAcumuladoDao(CtAcumuladoDao ctAcumuladoDao) {
		this.ctAcumuladoDao = ctAcumuladoDao;
	}

	public void setCtTipoAcumuladoDao(CtTipoAcumuladoDao ctTipoAcumuladoDao) {
		this.ctTipoAcumuladoDao = ctTipoAcumuladoDao;
	}

	public void setHqlUtilDao(HqlUtilDao hqlUtilDao) {
		this.hqlUtilDao = hqlUtilDao;
	}
}
