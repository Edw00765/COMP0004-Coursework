package uk.ac.ucl.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;



public class Model {
  private DataFrame dataFrame = new DataFrame();
  private DataLoader dataLoader = new DataLoader();

  public void readFile(String pathToCsv) {
    dataFrame = dataLoader.CSVtoDataFrame(pathToCsv);
  }

  public void addColumn() {
    Column newColumn = new Column();
    dataFrame.addColumn(newColumn);
  }

  public DataFrame getDataFrame() {
    return dataFrame;
  }

  public List<String> getColumnNames() {
    return dataFrame.getColumnNames();
  }

  public int getRowCount() {
    return dataFrame.getRowCount();
  }

  public List<String> getPatientID() {
    List<String> ID = new ArrayList<>();
    for (int i = 0; i < dataFrame.getRowCount(); i++) {
      ID.add(dataFrame.getValue("ID", i));
    }
    return ID;
  }

  public String getValue(String columnName, int row) {
    return dataFrame.getValue(columnName, row);
  }

  public void deletePatient(int row) {
    dataFrame.deleteRow(row);
  }

  public void putValue(String columnName, int row, String value) {
    dataFrame.putValue(columnName, row, value);
  }

  public void addValue(String columnName, String value) {
    dataFrame.addValue(columnName, value);
  }

  //returns the entire details of the patient from their ID
  public List<String> getPatientDetail(String ID) {
    List<String> patientDetail = new ArrayList<>();
    int index = dataFrame.getColumnIndex("ID");
    for (int i = 0; i < dataFrame.getRowCount(); i++) {
      if (dataFrame.getValue("ID", i).equals(ID)) {
        for (String columnNames : dataFrame.getColumnNames()) {
          patientDetail.add(dataFrame.getValue(columnNames, i));
        }
      }
    }
    return patientDetail;
  }

  public int calculateAgeFromBirthdate(String birthdateString, String deathDateString) {
    LocalDate birthdate = LocalDate.parse(birthdateString);
    LocalDate endDate;
    if (deathDateString != null && !deathDateString.isEmpty()) {
      endDate = LocalDate.parse(deathDateString);
    } else {
      endDate = LocalDate.now();
    }
    Period period = Period.between(birthdate, endDate);
    return period.getYears();
  }


  public List<Integer> calculateAllAges() {
    List<Integer> ages = new ArrayList<>();
    Column birthDateColumn = dataFrame.getColumnFromName("BIRTHDATE");
    Column deathDateColumn = dataFrame.getColumnFromName("DEATHDATE");

    for (int i = 0; i < dataFrame.getRowCount(); i++) {
      String birthDateString = birthDateColumn.getRowValue(i);
      String deathDateString = deathDateColumn.getRowValue(i);
      try {
        int age = calculateAgeFromBirthdate(birthDateString, deathDateString);
        ages.add(age);
      } catch (DateTimeParseException e) {
        System.err.println("Error parsing date in row " + i + ": " + e.getMessage());
      }
    }
    return ages;
  }

  public Map<Integer, Integer> getAgeDistribution() {
    List<Integer> ages = calculateAllAges();
    Map<Integer, Integer> distribution = new HashMap<>();
    for (Integer i = 0; i < 131; i++) {
      distribution.put(i, 0);
    }
    for (Integer age : ages) {
      Integer count = distribution.get(age);
      if (age < 0) {
        distribution.put(0, distribution.get(0) + 1);
      } else if (age > 130) {
        distribution.put(130, distribution.get(130) + 1);
      } else {
        distribution.put(age, distribution.get(age) + 1);
      }
    }
    return distribution;
  }

  public List<String> getOldestPatient() {
    List<String> oldestPatient = new ArrayList<>();
    int oldestAge = 0;
    List<Integer> oldestPatientIndex = new ArrayList<>();
    List<Integer> ages = calculateAllAges();
    for (int i = 0; i < ages.size(); i++) {
      if (ages.get(i) > oldestAge) {
        oldestPatientIndex.clear();
        oldestPatientIndex.add(i);
        oldestAge = ages.get(i);
      } else if (ages.get(i) == oldestAge) {
        oldestPatientIndex.add(i);
      }
    }
    for (Integer index : oldestPatientIndex) {
      for (String columnName : dataFrame.getColumnNames()) {
        oldestPatient.add(dataFrame.getValue(columnName, index));
      }
    }
    return oldestPatient;
  }

  public List<String> getMalePatients() {
    List<String> males = new ArrayList<>();
    for (int i = 0; i < dataFrame.getRowCount(); i++) {
      if (dataFrame.getValue("GENDER", i).equals("M")) {
        for (String columnName : dataFrame.getColumnNames()) {
          males.add(dataFrame.getValue(columnName, i));
        }
      }
    }
    return males;
  }

  public List<String> getFemalePatients() {
    List<String> males = new ArrayList<>();
    for (int i = 0; i < dataFrame.getRowCount(); i++) {
      if (dataFrame.getValue("GENDER", i).equals("F")) {
        for (String columnName : dataFrame.getColumnNames()) {
          males.add(dataFrame.getValue(columnName, i));
        }
      }
    }
    return males;
  }


  public List<String> searchFor(String keyword) {
    List<String> filteredResult = new ArrayList<String>();
    for (int i = 0; i < dataFrame.getRowCount(); i++) {
      //row_used is to ensure that the same patient isn't added twice
      int row_used = 0;
      for (String columnName : dataFrame.getColumnNames()) {
        String value = dataFrame.getValue(columnName, i);
        if (value.contains(keyword) && row_used == 0) {
          for (String name : dataFrame.getColumnNames()) {
            String rowValue = dataFrame.getValue(name, i);
            filteredResult.add(rowValue);
            row_used++;
          }
        }
      }
    }
    return filteredResult;
  }

  public void DataFrametoJSON(DataFrame dataFrame, String filePath) {
    JSONWriter jsonWriter = new JSONWriter();
    try {
      jsonWriter.writeDataFrameToJson(dataFrame, filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
