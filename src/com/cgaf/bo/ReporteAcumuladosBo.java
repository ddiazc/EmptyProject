package com.cgaf.bo;

import java.util.Date;
import java.util.List;

import com.cgaf.model.CtAcumulado;
import com.cgaf.model.CtConcepto;
import com.cgaf.model.CtTipoAcumulado;
import com.cgaf.model.CtVariable;
import com.cgaf.vo.DatosGenericosVo;

public interface ReporteAcumuladosBo {

	List<CtTipoAcumulado> findAllCtTipoAcumulado() throws Exception;

	List<CtAcumulado> findAllCtAcumulado() throws Exception;
	
	List<Integer> getAcumuladosByTipoAcum(List<Integer> tipoAcum, List<CtAcumulado> list1)
			throws Exception;
	
	List<String> getVariablesByTipom(List<String> tipo, List<CtVariable> list1)
			throws Exception;
	
	List<CtAcumulado> getCtAcumuladosByIds(List<Integer> ids, List<CtAcumulado> catalogo)
			throws Exception;
	
	DatosGenericosVo getReporteAcumulado(List<CtConcepto> listConceptos, CtAcumulado acumulado, Date fechaInicio, Date fechaFin)
			throws Exception;
}
