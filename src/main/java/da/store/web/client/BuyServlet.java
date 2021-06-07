package da.store.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import da.store.models.Goods;
import da.store.models.ShoppingCart;
import da.store.service.BusinessService;
import da.store.service.impl.BusinessServiceImpl;

@WebServlet("/client/BuyServlet")
public class BuyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static BusinessService service = new BusinessServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String goodsID = request.getParameter("id");
            
            ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
                request.getSession().setAttribute("cart", cart);
            }
            if (goodsID != null && goodsID.trim().equals("") == false) {
                Goods goods = service.findGoods(goodsID);
                cart.add(goods);
            }
            request.getRequestDispatcher("/jsps/client/cart.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", "购买失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
