package servlets;

import classes.Product;
import services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryServlet extends HttpServlet {
    private static List<Product> orderedProducts = new ArrayList<Product>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userUId = (String) req.getSession().getAttribute("user_id");
        String sql = "SELECT pl.* FROM order_history oh JOIN product_list pl ON oh.product_id = pl.id " +
                "WHERE pl.user_uid='" + userUId + "';";

        ProductService productService = new ProductService();
        orderedProducts = productService.loadProductListFromDB(sql);
        req.setAttribute("orderHistory", orderedProducts);

        req.getRequestDispatcher("/pages/orderHistory.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext();
        super.doPost(req, resp);
    }
}
