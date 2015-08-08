package com.cgaf.dao.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.cgaf.dao.HqlUtilDao;
import com.cgaf.model.CtAcumulado;
import com.cgaf.model.CtConcepto;
import com.cgaf.model.CtVariable;
import com.cgaf.model.HtGeneric;
import com.cgaf.util.FuncionesUtil;

public class HqlUtilDaoImpl extends JdbcDaoSupport implements HqlUtilDao {
	
	private static final Logger log = Logger.getLogger(HqlUtilDaoImpl.class);

	@SuppressWarnings("rawtypes")
	@Override
	public List<HtGeneric> executeQuery(CtConcepto object, String fechaIni, String fechaFin) {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT ID_PEE ID, FECH_PERIODO FECHA, NUM_PERIODO NP, ");
		hql.append("ID_VARIABLE VAR, VAL_VALOR VALOR, ID_VERSION VERSION FROM ");
		hql.append(object.getCtTabla().getDescTabla());
		hql.append(" WHERE ID_VARIABLE = ");
		hql.append(object.getCtVariable().getIdVariable());
		hql.append(" AND FECH_PERIODO BETWEEN TO_DATE ('");
		hql.append(fechaIni);
		hql.append("', 'dd/mm/yy hh24:mi:ss') AND TO_DATE('");
		hql.append(fechaFin);
		hql.append("', 'dd/mm/yy hh24:mi:ss')");
		try {
			List<HtGeneric> listOfGeneric = new ArrayList<HtGeneric>();
			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(hql.toString());
			for (Map row : rows) {
				HtGeneric genericObj = new HtGeneric();
				genericObj.setIdPee(Integer.parseInt(row.get("ID").toString()));
				genericObj.setFechPeriodo((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)row.get("Fecha").toString())));
				genericObj.setNumPeriodo(Integer.parseInt(row.get("NP").toString()));
				genericObj.setCtVariable(new CtVariable(Integer.parseInt(row.get("VAR").toString()), ""));
				genericObj.setValValor(Double.parseDouble(row.get("VALOR").toString()));
				genericObj.setIdPee(row.get("VERSION") != null ? Integer.parseInt(row.get("VERSION").toString()) : 0);
				listOfGeneric.add(genericObj);
			}
			log.debug("Se obtivieron " + listOfGeneric.size() + " registros para la variable " + object.getCtVariable().getIdVariable());
			return listOfGeneric;
		} catch (Exception e) {
			log.error("Ocurrio un error al realizar busqueda con HQL." , e);
			return null;
		}
	}
	
	@Override
	public List<Map<String, Object>> getReporteAcumulado(List<CtConcepto> listConceptos, CtAcumulado acumulado,
			Date fechaInicio, Date fechaFin) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String formatoAcum = acumulado.getFormatoAgrupacionAcumulado();
		
		String selectQuery = "select TO_CHAR(t0.FECH_PERIODO, '" + formatoAcum + "') \"Fecha inicio\"";
		String fromQuery = " from ";
		String whereQuery = " where ";
		String groupQuery = " group by TO_CHAR(t0.FECH_PERIODO, '" + acumulado.getFormatoAgrupacionAcumulado() + "')"
				+ " order by TO_CHAR(t0.FECH_PERIODO, '" + acumulado.getFormatoAgrupacionAcumulado() + "')";
		String queryString;

		String funcion = acumulado.getFuncionAcumulado();
		CtConcepto concepto;
		
		for (int i = 0; i < listConceptos.size(); i++) {
			concepto = listConceptos.get(i);
			
			selectQuery += ", " + funcion + "(t" + i + ".VAL_VALOR) \"" + concepto.getCtVariable().getIdVariable() + "\"";
			if (i==0) {
				fromQuery += concepto.getCtTabla().getDescTabla() + " t" + i;
				whereQuery += " t" + i + ".ID_VARIABLE = " + concepto.getCtVariable().getIdVariable();
			} else {
				fromQuery += " join " + concepto.getCtTabla().getDescTabla() + " t" + i
						+ " on t" + (i-1) + ".FECH_PERIODO = t" + i + ".FECH_PERIODO";
				whereQuery += " and t" + i + ".ID_VARIABLE = " + concepto.getCtVariable().getIdVariable();
			}
		}
		
		whereQuery += " and trunc(t1.FECH_PERIODO) BETWEEN '";
		if (formatoAcum.equals("yyyy/mm")) {
			whereQuery += format.format(FuncionesUtil.getFirstDayMonth(fechaInicio));
		} else if (formatoAcum.equals("yyyy")) {
			whereQuery += format.format(FuncionesUtil.getFirstDayYear(fechaInicio));
		} else {
			whereQuery += format.format(fechaInicio);
		}
		whereQuery += "' and '" + format.format(fechaFin) + "'";
		
		queryString = selectQuery + fromQuery + whereQuery + groupQuery;
		log.debug(queryString);

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(queryString);
		return rows;
	}
	
}
