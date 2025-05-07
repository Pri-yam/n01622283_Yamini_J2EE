package com.example.ejbtutorial;

import com.example.ejb.CalculatorBean;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @EJB
    private CalculatorBean calculatorBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int add_result = calculatorBean.add(10, 5);
        int minus_result = calculatorBean.subtract(10, 5);
        int multiply_result = calculatorBean.multiply(10, 5);
        int divide_result = calculatorBean.divide(10, 5);

        response.getWriter().println("Addition: 10 + 5 = " + add_result);
        response.getWriter().println("Substraction: 10 -5 = " + minus_result);
        response.getWriter().println("Mulitplication: 10 * 5 = " + multiply_result);
        response.getWriter().println("Division: 10 / 5 = " + divide_result);
    }
}
