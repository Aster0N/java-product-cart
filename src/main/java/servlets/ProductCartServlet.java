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

public class ProductCartServlet extends HttpServlet {
    private static int totalAmount;
    private static List<Product> productCart = new ArrayList<Product>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sql = "SELECT pl.* FROM favorite_products fp JOIN product_list pl ON fp.product_id = pl.id;";
        // load data from db
        ProductService productService = new ProductService();
        productCart = productService.loadProductListFromDB(sql);

        req.setAttribute("productCart", productCart);


        req.getRequestDispatcher("/pages/productCart.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }
}
