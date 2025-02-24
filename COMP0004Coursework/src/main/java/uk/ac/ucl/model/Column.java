package uk.ac.ucl.model;
import java.util.ArrayList;
public class Column {
    private String name;
    ArrayList<String> rows;
    public Column(){
        rows = new ArrayList<String>();
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getSize(){
        return rows.size();
    }
    public String getRowValue(int row_num){
        return rows.get(row_num);
    }
    public void setRowValue(int row_num, String value){
        rows.set(row_num, value);
    }
    public void addRowValue(String value){
        rows.add(value);
    }
    public void deleteRow(int rowIndex){
        rows.remove(rowIndex);
    }
}
