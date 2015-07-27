package com.cgaf.util.excel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface FileManager {
	
	/**
	 * Metodo que da formato a los Headers de un documento XLS.
	 * 
	 * @param document Documento XLS
	 * @throws Exception
	 * 
	 */
	void formatHeaders(HSSFWorkbook document) throws Exception;
	
	/**
	 * Metodo que da formato los renglones de un documento XLS.
	 * 
	 * @param document Documento XLS
	 * @throws Exception
	 * 
	 */
	void formatRows(HSSFWorkbook document) throws Exception;
	
	/**
	 * Metodo que centra todas las celdas del documento XLS.
	 * 
	 * @param document Documento XLS. 
	 * @throws Exception
	 * 
	 */
	void styleCells(HSSFWorkbook document) throws Exception;
	
	/**
	 * Metodo que agrega renglones al documento XLS.
	 * 
	 * @param document Documento XLS.
	 * @param rowToAdd Renglon a agregar en el archivo XLS.
	 * @throws Exception
	 * 
	 */
	void addRow(HSSFWorkbook document, List<String> rowToAdd) throws Exception;
	
	/**
	 * Metodo que termina el formato del documeto XLS.
	 * 
	 * @param document Documento XLS.
	 * @throws Exception
	 * 
	 */
	void formatXLS(HSSFWorkbook document) throws Exception;
	
	
}
