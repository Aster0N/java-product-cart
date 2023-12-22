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
import java.util.Objects;

public class ProductCartServlet extends HttpServlet {
    private static float totalAmount = 0;
    private static List<Product> productCart = new ArrayList<Product>();
    private void calculateTotalAmount() {
        totalAmount = 0;
        for (Product product : productCart) {
            totalAmount += product.getPrice();
        }
        double roundedAmount = Math.round(totalAmount * Math.pow(10, 2)) / Math.pow(10, 2);
        totalAmount = (float) roundedAmount;
    }
    private boolean removeProductFromCart(String uId) {
        for (int i = 0; i < productCart.size(); i++) {
            Product product = productCart.get(i);
            if(Objects.equals(product.getUId(), uId)) {
                boolean isInCart = product.getIsInCart();
                product.setIsInCart(!isInCart);
                productCart.remove(i);
                return product.getIsInCart();
            }
        }
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sql = "SELECT pl.* FROM product_cart pc JOIN product_list pl ON pc.product_id = pl.id;";
        // load data from db
        ProductService productService = new ProductService();
        productCart = productService.loadProductListFromDB(sql);
        calculateTotalAmount();

        req.setAttribute("productCart", productCart);
        if(totalAmount > 0) {
            req.setAttribute("totalAmount", totalAmount);
        }

        req.getRequestDispatcher("/pages/productCart.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext();
        String userUId = (String) req.getSession().getAttribute("user_id");
        // remove from product cart
        String productInCart = req.getParameter("remove-from-cart");
        if(productInCart != null && !productInCart.isEmpty()) {
            boolean isInCart = removeProductFromCart(productInCart);
            calculateTotalAmount();
            ProductService productService = new ProductService();
            productService.updateProductBooleanFieldById("is_in_cart", isInCart, productInCart, userUId);
            req.setAttribute("productCart", productCart);
            resp.sendRedirect("/app/product-cart");
            return;
        }
        super.doPost(req, resp);
    }
}
