package com.example.jmstutorial;

import java.io.*;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/send")
public class HelloServlet extends HttpServlet {

    @Resource(lookup = "java:/jms/queue/TestQueue")
    private Queue queue;

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String text = req.getParameter("message");

        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(queue, text);
        }

        req.setAttribute("message", text);
        req.getRequestDispatcher("/display.jsp")
                .forward(req, resp);
    }
}