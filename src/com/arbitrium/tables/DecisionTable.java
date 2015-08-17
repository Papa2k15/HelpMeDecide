package com.arbitrium.tables;

import javax.swing.table.AbstractTableModel;

public class DecisionTable extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final String[] columnNames = {"ID","Title","Description"};
    private Object[][] data = {{"N/A","N/A","N/A"}};
    
    public DecisionTable(){}
    
    public DecisionTable(Object[][] data){
    	this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}