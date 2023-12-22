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

public class FavoriteProductsServlet extends HttpServlet {
    private static List<Product> favoriteProductList = new ArrayList<Product>();
    private boolean removeProductFromFavorite(String uId) {
        for (int i = 0; i < favoriteProductList.size(); i++) {
            Product product = favoriteProductList.get(i);
            if(Objects.equals(product.getUId(), uId)) {
                boolean isFavorite = product.getIsFavorite();
                product.setIsFavorite(!isFavorite);
                favoriteProductList.remove(i);
                return product.getIsFavorite();
            }
        }
        return false;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sql = "SELECT pl.* FROM favorite_products fp JOIN product_list pl ON fp.product_id = pl.id;";
        // load data from db
        ProductService productService = new ProductService();
        favoriteProductList = productService.loadProductListFromDB(sql);

        req.setAttribute("favoriteProductList", favoriteProductList);

        req.getRequestDispatcher("/pages/favoriteProducts.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext();
        String userUId = (String) req.getSession().getAttribute("user_id");
        // remove from favorite
        String favoriteProduct = req.getParameter("remove-from-favorite");
        if(favoriteProduct != null && !favoriteProduct.isEmpty()) {
            boolean isFavorite = removeProductFromFavorite(favoriteProduct);
            ProductService productService = new ProductService();
            productService.updateProductBooleanFieldById("is_favorite", isFavorite, favoriteProduct, userUId);
            req.setAttribute("favoriteProductList", favoriteProductList);
            resp.sendRedirect("/app/favorite");
            return;
        }
        super.doPost(req, resp);
    }
}
