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

import classes.User;
import services.ProductService;
import services.db.UserService;

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
    private boolean addProductToCart(String uId) {
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if(Objects.equals(product.getUId(), uId)) {
                boolean isInCart = product.getIsInCart();
                product.setIsInCart(!isInCart);
                productList.set(i, product);
                return product.getIsInCart();
            }
        }
        return false;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UserService userService = new UserService();
        ProductService productService = new ProductService();

        String userUId = (String) req.getSession().getAttribute("user_id");
        User user = userService.getUserByUId(userUId);
        productList = productService.getUserProductListByUId(userUId);

        req.setAttribute("user", user);
        req.setAttribute("productList", productList);

        req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext();
        String userUId = (String) req.getSession().getAttribute("user_id");
        // add/remove from favorite
        String favoriteProduct = req.getParameter("add-to-favorite");
        if(favoriteProduct != null && !favoriteProduct.isEmpty()) {
            // change is_favorite state on client
            boolean isFavorite = addProductToFavorite(favoriteProduct);
            ProductService productService = new ProductService();
            productService.updateProductBooleanFieldById("is_favorite", isFavorite, favoriteProduct, userUId);
            resp.sendRedirect("/app/");
            return;
        }

        // add/remove to product cart
        String productToCart = req.getParameter("add-to-cart");
        if(productToCart != null && !productToCart.isEmpty()) {
            // change is_in_cart on client
            boolean isInCart = addProductToCart(productToCart);
            ProductService productService = new ProductService();
            productService.updateProductBooleanFieldById("is_in_cart", isInCart, productToCart, userUId);
            resp.sendRedirect("/app/");
            return;
        }

        // create new product
        String productName = req.getParameter("name");
        String productDescription = req.getParameter("description");
        float productPrice = Float.parseFloat(req.getParameter("price"));

        Product newProduct = new Product(productName, productDescription, productPrice, userUId);
        productList.add(newProduct);
        ProductService productService = new ProductService();
        productService.addProduct(newProduct);

        // refresh page with whole content
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);

        super.doPost(req, resp);
    }
}
