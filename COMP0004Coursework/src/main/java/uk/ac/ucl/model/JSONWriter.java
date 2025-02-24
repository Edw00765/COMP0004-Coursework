package uk.ac.ucl.model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class JSONWriter {
    public void writeDataFrameToJson(DataFrame dataFrame, String filePath) throws IOException {
        List<String> columnNames = dataFrame.getColumnNames();
        int rowCount = dataFrame.getRowCount();
        List<Map<String, String>> patientList = new ArrayList<>();
        Map<String, List<Map<String, String>>> toJSON = new HashMap<>();

        for (int i = 0; i < rowCount; i++) {
            Map<String, String> patient = new HashMap<>();
            for (String columnName : columnNames) {
                String value = dataFrame.getValue(columnName, i);
                patient.put(columnName, value);
            }
            patientList.add(patient);
        }
        toJSON.put("patients", patientList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), toJSON);
    }
}
