package com.cgaf.util.excel.impl;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cgaf.util.excel.FileManager;

public class FileManagerImpl implements FileManager {

	@Override
	public void formatHeaders(HSSFWorkbook document) throws Exception {
	}

	@Override
	public void formatRows(HSSFWorkbook document) throws Exception {
	}

	@Override
	public void styleCells(HSSFWorkbook document) throws Exception {
		HSSFSheet sheet = document.getSheetAt(0);
        HSSFCellStyle cellStyle = document.createCellStyle();
        HSSFFont font = document.createFont();
        font.setFontHeightInPoints((short)8);
        font.setFontName("Arial");
        cellStyle.setFont(font);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_JUSTIFY);
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
        	HSSFRow rows = sheet.getRow(i);
        	for (int j = 0; j < rows.getPhysicalNumberOfCells(); j++) {
        		HSSFCell cell = rows.getCell(j);
        		cell.setCellStyle(cellStyle);
        	}
        }
	}

	@Override
	public void addRow(HSSFWorkbook document, List<String> rowToAdd) throws Exception {
		HSSFSheet sheet = document.getSheetAt(0);
		sheet.shiftRows(1, 2, 1);
		sheet.createRow(1);
		HSSFRow rows = sheet.getRow(1);
		int i = 0;
		for (String item : rowToAdd) {
			sheet.setColumnWidth(i, 20*256);
			HSSFCell cell = rows.createCell(i);
    		cell.setCellValue(item);
    		i++;
		}
	}

	@Override
	public void formatXLS(HSSFWorkbook document) throws Exception {
		HSSFSheet sheet = document.getSheetAt(0);
		document.setSheetName(document.getSheetIndex(sheet), "Valores5Min");
	}
	
}
