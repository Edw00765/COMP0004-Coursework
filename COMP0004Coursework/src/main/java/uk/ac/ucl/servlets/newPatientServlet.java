package uk.ac.ucl.servlets;

import uk.ac.ucl.model.CSVWriter;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/newPatientAdded.html")
public class newPatientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        List<String> columnNames = model.getColumnNames();
        for (String columnName : columnNames) {
            String paramValue = request.getParameter(columnName);
            model.addValue(columnName, paramValue);
        }
        CSVWriter.dataFrameToCSV(model.getDataFrame(), "data/patients101.csv" );
        response.sendRedirect("index.html");
    }

}