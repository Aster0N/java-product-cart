package servlets;

import services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class RegistrationServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordConfirmation = req.getParameter("password-confirmation");

        if(!Objects.equals(password, passwordConfirmation)) {
            resp.sendRedirect("/app/register");
        }

        AuthService authService = new AuthService();
        String userUId = authService.signUp(login, password);
        if (!userUId.isEmpty()) {
            req.getSession().setAttribute("user_id", userUId);
            resp.sendRedirect( "/app/");
        } else {
            req.setAttribute("error", "This user already exists");
            System.out.println("[SIGN_UP_USER_ERROR]: User already exists");
            req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
            super.doPost(req, resp);
        }
        super.doPost(req, resp);
    }
}
