package servlets;

import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginService loginService = new LoginService();
        int userId = loginService.auth(username, password);
        if (userId != -1) {
            request.getSession().setAttribute("user_id", userId);
            response.sendRedirect( "/dashboard");
        } else {
            request.setAttribute("errorMessage", "error login or pass");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            super.doPost(request, response);
        }
    }
}
