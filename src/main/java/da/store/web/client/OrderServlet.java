package da.store.web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import da.store.models.Order;
import da.store.models.ShoppingCart;
import da.store.models.User;
import da.store.service.BusinessService;
import da.store.service.impl.BusinessServiceImpl;

@WebServlet("/client/OrderServlet")
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static BusinessService service = new BusinessServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("create".equals(method)) {
            create(request, response);
        }
        if ("getAll".equals(method)) {
            getAll(request, response);
        }
        if ("find".equals(method)) {
            find(request, response);
        }
        if ("set".equals(method)) {
            set(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
            User user = (User) request.getSession().getAttribute("user");
            if (cart.getItems().size() > 0) {
                service.createOrder(cart, user);
                request.getSession().removeAttribute("cart");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "购物车空，无法创建订单");
                request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "订单创建失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            List<Order> list = service.findOrder(id, status);
            request.setAttribute("list", list);
            request.getRequestDispatcher("/jsps/admin/bms-listorder.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "查询失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

    private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Order order = service.findOrder(id);
            request.setAttribute("order", order);
            request.getRequestDispatcher("/jsps/admin/bms-orderdetail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "查询失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

    private void set(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            service.updateOrder(id, status);
            request.setAttribute("message", "修改成功");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "修改失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }
}
