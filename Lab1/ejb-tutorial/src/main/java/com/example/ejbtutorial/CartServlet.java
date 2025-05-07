package com.example.ejbtutorial;

import com.example.ejb.CartBean;

import jakarta.ejb.EJB;
import javax.naming.InitialContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);

        CartBean cart = (CartBean) session.getAttribute("cart");

        // Look up the stateful EJB if not stored in session yet
        if (cart == null) {
            try {
                InitialContext ctx = new InitialContext();
                cart = (CartBean) ctx.lookup("java:module/CartBean");
                session.setAttribute("cart", cart);
            } catch (Exception e) {
                resp.getWriter().println("Error looking up CartBean: " + e.getMessage());
                return;
            }
        }

        String action = req.getParameter("action");
        String item = req.getParameter("item");

        if ("add".equalsIgnoreCase(action) && item != null) {
            cart.addItem(item);
        } else if ("remove".equalsIgnoreCase(action) && item != null) {
            cart.removeItem(item);
        }

        resp.setContentType("text/plain");
        resp.getWriter().println("Current Cart: " + cart.getItems());
    }
}
