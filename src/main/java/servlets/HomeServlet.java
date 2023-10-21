package servlets;

import classes.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static helpers.Helpers.fillProductList;

public class HomeServlet extends HttpServlet {
    private static List<Product> productList = new ArrayList<Product>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(productList.isEmpty()) {
            fillProductList(productList);
        }

        req.setAttribute("productList", productList);

        req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);
        resp.setContentType("text/html");

        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext();
        String productTitle = req.getParameter("title");
        String productDescription = req.getParameter("description");
        float productPrice = Float.parseFloat(req.getParameter("price"));

        Product newProduct = new Product(productTitle, productDescription, productPrice);
        productList.add(newProduct);
        newProduct.printProductInfo();

        super.doPost(req, resp);
    }
}
