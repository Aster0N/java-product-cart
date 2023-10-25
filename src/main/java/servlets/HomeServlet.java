package servlets;

import classes.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import services.ProductService;

public class HomeServlet extends HttpServlet {
    private static List<Product> productList = new ArrayList<Product>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // load data from db
        ProductService productService = new ProductService();
        productList = productService.loadProductListFromDB();

        req.setAttribute("productList", productList);

        req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);
        resp.setContentType("text/html");

        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext();
        String productName = req.getParameter("name");
        String productDescription = req.getParameter("description");
        float productPrice = Float.parseFloat(req.getParameter("price"));

        Product newProduct = new Product(productName, productDescription, productPrice);
        productList.add(newProduct);
        ProductService productService = new ProductService();
        productService.addProduct(newProduct);

        // refresh page with whole content
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);

        super.doPost(req, resp);
    }
}
