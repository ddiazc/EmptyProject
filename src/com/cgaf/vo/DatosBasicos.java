package com.cgaf.vo;

import java.sql.Timestamp;

/**
*
* @author Franck RVX
*/

public class DatosBasicos {
	
	private int id;
	private Timestamp fechaIni;
	private Timestamp fechaFin;
	private double carga;
	private double ctungg;
	private double ctovg;
	private double tatm;
	private double hatm;
	private double patm;
	private double cDDreal;
	private double potenciaV;
	private double potencia;
	private double enTotal;
	private double enCinco;
	private double enLin1PrinEnt;
	private double enLin1PrinSal;
	private double enLin1RespEnt;
	private double enLin1RespSal;
	private double enLin2PrinEnt;
	private double enLin2PrinSal;
	private double enLin2RespEnt;
	private double enLin2RespSal;
	private double pCIgas;
	private double pCSgas;
	private double densGas;
	private double factPot;
	private double presCond;
	private double enLin1PrinReacQ1;
	private double enLin1PrinReacQ4;
	private double enLin1RespReacQ1;
	private double enLin1RespReacQ4;
	private double enLin2PrinReacQ1;
	private double enLin2PrinReacQ4;
	private double enLin2RespReacQ1;
	private double enLin2RespReacQ4;
	private double pCIgasM3;
	private double pCSgasM3;
	
	public DatosBasicos() {
		super();
	}
	public DatosBasicos(int id, Timestamp fechaIni, Timestamp fechaFin,
			double carga, double ctungg, double ctovg, double tatm,
			double hatm, double patm, double cDDreal, double potenciaV,
			double potencia, double enTotal, double enCinco,
			double enLin1PrinEnt, double enLin1PrinSal, double enLin1RespEnt,
			double enLin1RespSal, double enLin2PrinEnt, double enLin2PrinSal,
			double enLin2RespEnt, double enLin2RespSal, double pCIgas,
			double pCSgas, double densGas, double factPot, double presCond,
			double enLin1PrinReacQ1, double enLin1PrinReacQ4,
			double enLin1RespReacQ1, double enLin1RespReacQ4,
			double enLin2PrinReacQ1, double enLin2PrinReacQ4,
			double enLin2RespReacQ1, double enLin2RespReacQ4, double pCIgasM3,
			double pCSgasM3) {
		super();
		this.id = id;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.carga = carga;
		this.ctungg = ctungg;
		this.ctovg = ctovg;
		this.tatm = tatm;
		this.hatm = hatm;
		this.patm = patm;
		this.cDDreal = cDDreal;
		this.potenciaV = potenciaV;
		this.potencia = potencia;
		this.enTotal = enTotal;
		this.enCinco = enCinco;
		this.enLin1PrinEnt = enLin1PrinEnt;
		this.enLin1PrinSal = enLin1PrinSal;
		this.enLin1RespEnt = enLin1RespEnt;
		this.enLin1RespSal = enLin1RespSal;
		this.enLin2PrinEnt = enLin2PrinEnt;
		this.enLin2PrinSal = enLin2PrinSal;
		this.enLin2RespEnt = enLin2RespEnt;
		this.enLin2RespSal = enLin2RespSal;
		this.pCIgas = pCIgas;
		this.pCSgas = pCSgas;
		this.densGas = densGas;
		this.factPot = factPot;
		this.presCond = presCond;
		this.enLin1PrinReacQ1 = enLin1PrinReacQ1;
		this.enLin1PrinReacQ4 = enLin1PrinReacQ4;
		this.enLin1RespReacQ1 = enLin1RespReacQ1;
		this.enLin1RespReacQ4 = enLin1RespReacQ4;
		this.enLin2PrinReacQ1 = enLin2PrinReacQ1;
		this.enLin2PrinReacQ4 = enLin2PrinReacQ4;
		this.enLin2RespReacQ1 = enLin2RespReacQ1;
		this.enLin2RespReacQ4 = enLin2RespReacQ4;
		this.pCIgasM3 = pCIgasM3;
		this.pCSgasM3 = pCSgasM3;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Timestamp fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Timestamp getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}
	public double getCarga() {
		return carga;
	}
	public void setCarga(double carga) {
		this.carga = carga;
	}
	public double getCtungg() {
		return ctungg;
	}
	public void setCtungg(double ctungg) {
		this.ctungg = ctungg;
	}
	public double getCtovg() {
		return ctovg;
	}
	public void setCtovg(double ctovg) {
		this.ctovg = ctovg;
	}
	public double getTatm() {
		return tatm;
	}
	public void setTatm(double tatm) {
		this.tatm = tatm;
	}
	public double getHatm() {
		return hatm;
	}
	public void setHatm(double hatm) {
		this.hatm = hatm;
	}
	public double getPatm() {
		return patm;
	}
	public void setPatm(double patm) {
		this.patm = patm;
	}
	public double getcDDreal() {
		return cDDreal;
	}
	public void setcDDreal(double cDDreal) {
		this.cDDreal = cDDreal;
	}
	public double getPotenciaV() {
		return potenciaV;
	}
	public void setPotenciaV(double potenciaV) {
		this.potenciaV = potenciaV;
	}
	public double getPotencia() {
		return potencia;
	}
	public void setPotencia(double potencia) {
		this.potencia = potencia;
	}
	public double getEnTotal() {
		return enTotal;
	}
	public void setEnTotal(double enTotal) {
		this.enTotal = enTotal;
	}
	public double getEnCinco() {
		return enCinco;
	}
	public void setEnCinco(double enCinco) {
		this.enCinco = enCinco;
	}
	public double getEnLin1PrinEnt() {
		return enLin1PrinEnt;
	}
	public void setEnLin1PrinEnt(double enLin1PrinEnt) {
		this.enLin1PrinEnt = enLin1PrinEnt;
	}
	public double getEnLin1PrinSal() {
		return enLin1PrinSal;
	}
	public void setEnLin1PrinSal(double enLin1PrinSal) {
		this.enLin1PrinSal = enLin1PrinSal;
	}
	public double getEnLin1RespEnt() {
		return enLin1RespEnt;
	}
	public void setEnLin1RespEnt(double enLin1RespEnt) {
		this.enLin1RespEnt = enLin1RespEnt;
	}
	public double getEnLin1RespSal() {
		return enLin1RespSal;
	}
	public void setEnLin1RespSal(double enLin1RespSal) {
		this.enLin1RespSal = enLin1RespSal;
	}
	public double getEnLin2PrinEnt() {
		return enLin2PrinEnt;
	}
	public void setEnLin2PrinEnt(double enLin2PrinEnt) {
		this.enLin2PrinEnt = enLin2PrinEnt;
	}
	public double getEnLin2PrinSal() {
		return enLin2PrinSal;
	}
	public void setEnLin2PrinSal(double enLin2PrinSal) {
		this.enLin2PrinSal = enLin2PrinSal;
	}
	public double getEnLin2RespEnt() {
		return enLin2RespEnt;
	}
	public void setEnLin2RespEnt(double enLin2RespEnt) {
		this.enLin2RespEnt = enLin2RespEnt;
	}
	public double getEnLin2RespSal() {
		return enLin2RespSal;
	}
	public void setEnLin2RespSal(double enLin2RespSal) {
		this.enLin2RespSal = enLin2RespSal;
	}
	public double getpCIgas() {
		return pCIgas;
	}
	public void setpCIgas(double pCIgas) {
		this.pCIgas = pCIgas;
	}
	public double getpCSgas() {
		return pCSgas;
	}
	public void setpCSgas(double pCSgas) {
		this.pCSgas = pCSgas;
	}
	public double getDensGas() {
		return densGas;
	}
	public void setDensGas(double densGas) {
		this.densGas = densGas;
	}
	public double getFactPot() {
		return factPot;
	}
	public void setFactPot(double factPot) {
		this.factPot = factPot;
	}
	public double getPresCond() {
		return presCond;
	}
	public void setPresCond(double presCond) {
		this.presCond = presCond;
	}
	public double getEnLin1PrinReacQ1() {
		return enLin1PrinReacQ1;
	}
	public void setEnLin1PrinReacQ1(double enLin1PrinReacQ1) {
		this.enLin1PrinReacQ1 = enLin1PrinReacQ1;
	}
	public double getEnLin1PrinReacQ4() {
		return enLin1PrinReacQ4;
	}
	public void setEnLin1PrinReacQ4(double enLin1PrinReacQ4) {
		this.enLin1PrinReacQ4 = enLin1PrinReacQ4;
	}
	public double getEnLin1RespReacQ1() {
		return enLin1RespReacQ1;
	}
	public void setEnLin1RespReacQ1(double enLin1RespReacQ1) {
		this.enLin1RespReacQ1 = enLin1RespReacQ1;
	}
	public double getEnLin1RespReacQ4() {
		return enLin1RespReacQ4;
	}
	public void setEnLin1RespReacQ4(double enLin1RespReacQ4) {
		this.enLin1RespReacQ4 = enLin1RespReacQ4;
	}
	public double getEnLin2PrinReacQ1() {
		return enLin2PrinReacQ1;
	}
	public void setEnLin2PrinReacQ1(double enLin2PrinReacQ1) {
		this.enLin2PrinReacQ1 = enLin2PrinReacQ1;
	}
	public double getEnLin2PrinReacQ4() {
		return enLin2PrinReacQ4;
	}
	public void setEnLin2PrinReacQ4(double enLin2PrinReacQ4) {
		this.enLin2PrinReacQ4 = enLin2PrinReacQ4;
	}
	public double getEnLin2RespReacQ1() {
		return enLin2RespReacQ1;
	}
	public void setEnLin2RespReacQ1(double enLin2RespReacQ1) {
		this.enLin2RespReacQ1 = enLin2RespReacQ1;
	}
	public double getEnLin2RespReacQ4() {
		return enLin2RespReacQ4;
	}
	public void setEnLin2RespReacQ4(double enLin2RespReacQ4) {
		this.enLin2RespReacQ4 = enLin2RespReacQ4;
	}
	public double getpCIgasM3() {
		return pCIgasM3;
	}
	public void setpCIgasM3(double pCIgasM3) {
		this.pCIgasM3 = pCIgasM3;
	}
	public double getpCSgasM3() {
		return pCSgasM3;
	}
	public void setpCSgasM3(double pCSgasM3) {
		this.pCSgasM3 = pCSgasM3;
	}

}
