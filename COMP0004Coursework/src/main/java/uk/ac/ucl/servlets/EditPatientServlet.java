package uk.ac.ucl.servlets;
import uk.ac.ucl.model.CSVWriter;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editPatient.html")
public class EditPatientServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        String patientID = request.getParameter("patientID");
        List<String> columnNames = model.getColumnNames();
        List<String> patientDetail = model.getPatientDetail(patientID);
        request.setAttribute("columnNames", columnNames);
        request.setAttribute("patientDetail", patientDetail);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/editPatient.jsp");
        dispatch.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        String patientID = request.getParameter("patientID");
        List<String> patientIDs = model.getPatientID();

        for (String columnName : model.getColumnNames()) {
            String updatedValue = request.getParameter(columnName);
            int row = patientIDs.indexOf(patientID);
            model.putValue(columnName, row, updatedValue);
        }
        CSVWriter.dataFrameToCSV(model.getDataFrame(), "data/patients101.csv" );
        response.sendRedirect("index.html");
    }
}