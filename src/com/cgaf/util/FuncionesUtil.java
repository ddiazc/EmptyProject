package com.cgaf.util;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.cgaf.model.CtConcepto;

public class FuncionesUtil {
	
	private FuncionesUtil() {}

	public static List<String> getEncabezados(List<CtConcepto> listConcepto, List<String> vals){
		List<String> encabezados = new ArrayList<>();
		boolean add = false;
		
		for (String string : vals) {
			for (CtConcepto ctConcepto : listConcepto) {
				if (string.equals(ctConcepto.getCtVariable().getIdVariable().toString())) {
					encabezados.add(ctConcepto.getCtVariable().getDescVariable());
					add = true;
				}
			}
			if (!add) {
				encabezados.add(string);
			}
			add = false;
		}
		return encabezados;
	}
	
	public static Date getFirstDayMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		return cal.getTime();
	}
	
	public static Date getFirstDayYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_YEAR, 1);

		return cal.getTime();
	}
	
	public static void main(String[] args) {
		
		System.out.println(FuncionesUtil.getFirstDayYear(new Date()));
		System.out.println(FuncionesUtil.getFirstDayMonth(new Date()));
	}
}
