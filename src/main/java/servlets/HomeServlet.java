package servlets;

import classes.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import services.ProductService;

public class HomeServlet extends HttpServlet {
    private static List<Product> productList = new ArrayList<Product>();
    private boolean addProductToFavorite(String uId) {
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if(Objects.equals(product.getUId(), uId)) {
                boolean isFavorite = product.getIsFavorite();
                product.setIsFavorite(!isFavorite);
                productList.set(i, product);
                return product.getIsFavorite();
            }
        }
        return false;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // load data from db
        ProductService productService = new ProductService();
        String sql = "select * from product_list;";
        productList = productService.loadProductListFromDB(sql);

        req.setAttribute("productList", productList);

        req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);
        resp.setContentType("text/html");

        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext();

        String favoriteProduct = req.getParameter("add-to-favorite");
        if(favoriteProduct != null && !favoriteProduct.isEmpty()) {
            // change is_favorite state on client
            boolean isFavorite = addProductToFavorite(favoriteProduct);
            ProductService productService = new ProductService();
            productService.addProductToFavorite(isFavorite, favoriteProduct);
            req.setAttribute("productList", productList);
            resp.sendRedirect("/app/");
            return;
        }

        String productName = req.getParameter("name");
        String productDescription = req.getParameter("description");
        float productPrice = Float.parseFloat(req.getParameter("price"));

        Product previousAddedProduct = productList.get(productList.size() - 1);
        if(Objects.equals(previousAddedProduct.getName(), productName) &&
                Objects.equals(previousAddedProduct.getDescription(), productDescription) &&
                previousAddedProduct.getPrice() == productPrice) {
            req.setAttribute("productList", productList);
            req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);
            return;
        }

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
