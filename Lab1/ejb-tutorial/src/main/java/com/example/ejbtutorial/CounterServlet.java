package com.example.ejbtutorial;

import com.example.ejb.VisitCounterBean;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/counter")
public class CounterServlet extends HttpServlet {

    @EJB
    private VisitCounterBean counterBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int newCount = counterBean.incrementAndGet();
        resp.setContentType("text/plain");
        resp.getWriter().println("Total Visits: " + newCount);
    }
}