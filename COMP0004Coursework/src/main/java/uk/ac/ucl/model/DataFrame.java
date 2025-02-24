package uk.ac.ucl.model;

import java.util.ArrayList;
import java.util.List;

public class DataFrame {
    private ArrayList<Column> columns;
    public DataFrame(){
        columns = new ArrayList<Column>();
    }
    public void addColumn(Column new_column){
        columns.add(new_column);
    }

    public List<String> getColumnNames(){
        List<String> nameList = new ArrayList<String>();
        for(int i=0; i<columns.size(); i++){
            String name = columns.get(i).getName();
            nameList.add(name);
        }
        return nameList;
    }

    public int getRowCount(){
        return columns.getFirst().getSize();
    }

    public int getColumnIndex(String columnName) {
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).getName().equals(columnName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Column name '" + columnName + "' not found.");
    }

    public String getValue(String columnName, int row){
        int column_index = getColumnIndex(columnName);
        return columns.get(column_index).getRowValue(row);
    }
    public void putValue(String columnName, int row, String value){
        int column_index = getColumnIndex(columnName);
        columns.get(column_index).setRowValue(row, value);
    }

    public void addValue(String columnName, String value){
        int column_index = getColumnIndex(columnName);
        columns.get(column_index).addRowValue(value);
    }
    public Column getColumn(int index){
        if (index < 0 || index >= columns.size()) {
            throw new IndexOutOfBoundsException("Column index out of bounds");
        }
        return columns.get(index);
    }
    public Column getColumnFromName(String columnName){
        int index = getColumnIndex(columnName);
        return getColumn(index);
    }
    public void deleteRow(int rowIndex){
        for (Column column : columns){
            column.deleteRow(rowIndex);
        }
    }

}
