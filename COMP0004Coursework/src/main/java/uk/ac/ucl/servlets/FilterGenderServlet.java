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

@WebServlet("/FilterGender.html")
public class FilterGenderServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        request.setAttribute("detailType", model.getColumnNames());
        request.setAttribute("genderFiltered", model.getFemalePatients());

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/FilterGender.jsp");
        dispatch.forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        request.setAttribute("detailType", model.getColumnNames());
        request.setAttribute("genderFiltered", model.getMalePatients());

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/FilterGender.jsp");
        dispatch.forward(request, response);
    }
}