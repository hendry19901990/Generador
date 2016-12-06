package com.extjs.generador;

import java.io.Serializable;

public class GmaAlias implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int aliacons;
	private String aliacamp;
	private String aliaalia;
	private String aliatabl;
	private int aliaesta;
	
	public int getAliacons() {
		return aliacons;
	}
	public void setAliacons(int aliacons) {
		this.aliacons = aliacons;
	}
	public String getAliacamp() {
		return aliacamp;
	}
	public void setAliacamp(String aliacamp) {
		this.aliacamp = aliacamp;
	}
	public String getAliaalia() {
		return aliaalia;
	}
	public void setAliaalia(String aliaalia) {
		this.aliaalia = aliaalia;
	}
	public String getAliatabl() {
		return aliatabl;
	}
	public void setAliatabl(String aliatabl) {
		this.aliatabl = aliatabl;
	}
	public int getAliaesta() {
		return aliaesta;
	}
	public void setAliaesta(int aliaesta) {
		this.aliaesta = aliaesta;
	}
	
	
	public String toString(){
		
		StringBuffer buf = new StringBuffer();
		buf.append("{ aliacons:"+aliacons);
		buf.append(" aliacamp, : '"+aliacamp +"'");
		buf.append(" aliaalia: '"+aliaalia+"'");
		buf.append(" aliatabl: '"+aliatabl+"'");
		buf.append(" aliaesta:"+aliaesta);
		buf.append("}");
		
		return buf.toString();
	}

}

