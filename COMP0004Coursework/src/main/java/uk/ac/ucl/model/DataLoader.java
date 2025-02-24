package uk.ac.ucl.model;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.File;

public class DataLoader {
    public DataFrame CSVtoDataFrame(String pathToCsv){
        DataFrame dataFrame = new DataFrame();
        File file = new File(pathToCsv);
        //This is added for error handling
        if (!file.exists()) {
            System.err.println("Error: The specified CSV file does not exist.");
            Column errorColumnID = new Column();
            Column errorColumnBirth = new Column();
            Column errorGender = new Column();
            errorGender.setName("GENDER");
            errorGender.addRowValue("");
            errorColumnID.setName("ID");
            errorColumnBirth.setName("BIRTHDATE");
            errorColumnID.addRowValue("Error: The specified CSV file does not exist.");
            errorColumnBirth.addRowValue("1000-01-01");
            dataFrame.addColumn(errorColumnID);
            dataFrame.addColumn(errorColumnBirth);
            dataFrame.addColumn(errorGender);
            return dataFrame;
        }

        try (Reader reader = new FileReader(pathToCsv);
             CSVParser column = new CSVParser(reader, CSVFormat.DEFAULT)) {

            int row_scanned = 0;
            for (CSVRecord row : column) {
                if (row_scanned == 0) {
                    for (String columnName : row) {
                        Column columni = new Column();
                        columni.setName(columnName);
                        dataFrame.addColumn(columni);
                    }
                } else {
                    int column_scanned = 0;
                    for (String value : row) {
                        String currentColumnName = dataFrame.getColumnNames().get(column_scanned);
                        dataFrame.addValue(currentColumnName, value);
                        column_scanned++;
                    }
                }
                row_scanned++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataFrame;
    }
}
