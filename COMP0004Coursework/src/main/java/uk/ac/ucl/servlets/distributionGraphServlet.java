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
import java.util.Map;

@WebServlet("/distribution.html")

public class distributionGraphServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        Map<Integer, Integer> distribution = model.getAgeDistribution();
        request.setAttribute("distribution", distribution);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/distributionGraph.jsp");
        dispatch.forward(request, response);
    }
}
