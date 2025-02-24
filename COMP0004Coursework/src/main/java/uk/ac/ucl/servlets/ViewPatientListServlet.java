package uk.ac.ucl.servlets;

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

@WebServlet("/patientList.html")
public class ViewPatientListServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Model model = ModelFactory.getModel();
    List<String> patientID = model.getPatientID();
    request.setAttribute("patientID", patientID);

    String patient = request.getParameter("patient");
    if (patient != null) {
      List<String> patientDetail = model.getPatientDetail(patient);
      List<String> detailType = model.getColumnNames();
      request.setAttribute("patientDetail", patientDetail);
      request.setAttribute("detailType", detailType);

      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/ID_details.jsp");
      dispatch.forward(request, response);
      return;
    }

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/patientList.jsp");
    dispatch.forward(request, response);
  }
}
