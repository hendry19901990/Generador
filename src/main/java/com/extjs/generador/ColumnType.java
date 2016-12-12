package com.extjs.generador;

public class ColumnType {
    private String name;
    private String type;
    private int data_length;
    private int data_scale;
    private boolean isnull;
    private boolean isprimarykey;
    private String alias;

    public ColumnType() {
    }

    public ColumnType(String name, String type, int data_length, int data_scale, boolean isnull, boolean isprimarykey) {
        this.name = name.toLowerCase();
        this.type = type;
        this.data_length = data_length;
        this.data_scale = data_scale;
        this.isnull = isnull;
        this.isprimarykey = isprimarykey;
        this.alias = name.toLowerCase();
    }

    public boolean isIsnull() {
        return this.isnull;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public void setType(String type) {
        this.type = type.toLowerCase();
    }

    public int getData_length() {
        return this.data_length;
    }

    public void setData_length(int data_length) {
        this.data_length = data_length;
    }

    public int getData_scale() {
        return this.data_scale;
    }

    public void setData_scale(int data_scale) {
        this.data_scale = data_scale;
    }
    

    public boolean isIsprimarykey() {
		return isprimarykey;
	}

	public void setIsprimarykey(boolean isprimarykey) {
		this.isprimarykey = isprimarykey;
	}
	

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String toString() {
        StringBuffer allCadena = new StringBuffer();
        allCadena.append("{");
        allCadena.append(" name: '" + this.name + "',");
        allCadena.append(" type: '" + this.type + "',");
        allCadena.append(" data_length: " + this.data_length + ",");
        allCadena.append(" isnull: " + this.isnull + ",");
        allCadena.append(" data_scale: " + this.data_scale);
        allCadena.append(" }");
        return allCadena.toString();
    }
}