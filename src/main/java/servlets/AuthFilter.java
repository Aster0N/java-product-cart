package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        boolean isUserLoggedIn = (session != null && session.getAttribute("user_id") != null);
        String requestURI = httpRequest.getRequestURI();
        if (!isUserLoggedIn && !requestURI.endsWith("/login") && !requestURI.endsWith("/register")) {
            httpResponse.sendRedirect("/app/login");
            return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
