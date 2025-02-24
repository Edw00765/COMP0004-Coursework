package uk.ac.ucl.model;
import java.io.FileWriter;
import java.io.IOException;
public class CSVWriter{
    public static void dataFrameToCSV(DataFrame dataFrame, String filePath){
        try (FileWriter csvWriter = new FileWriter(filePath)) {
            for (String columnName : dataFrame.getColumnNames()) {
                if (!columnName.equals("ZIP")) {
                    csvWriter.append(columnName).append(",");
                } else {
                    csvWriter.append(columnName);
                }
            }
            csvWriter.append("\n");
            int rowCount = dataFrame.getRowCount();
            for (int row = 0; row < rowCount; row++) {
                for (int column = 0; column < dataFrame.getColumnNames().size(); column++) {
                    if (column != dataFrame.getColumnNames().size() - 1){
                        String value = dataFrame.getValue(dataFrame.getColumn(column).getName(), row);
                        csvWriter.append(value).append(",");
                    } else {
                        String value = dataFrame.getValue(dataFrame.getColumn(column).getName(), row);
                        csvWriter.append(value);
                    }

                }
                csvWriter.append("\n");
            }

            csvWriter.flush();
        } catch (IOException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }

    }


}