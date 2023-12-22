package servlets;

import services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        AuthService authService = new AuthService();
        String userUId = authService.signIn(login, password);
        if (!userUId.isEmpty()) {
            request.getSession().setAttribute("user_id", userUId);
            response.sendRedirect( "/app/");
        } else {
            System.out.println("[AUTH_USER_ERROR]: Login or password error");
            request.getRequestDispatcher("/app/login").forward(request, response);
            super.doPost(request, response);
        }
    }
}
