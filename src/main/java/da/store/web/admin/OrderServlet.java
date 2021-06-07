package da.store.web.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import da.store.models.Order;
import da.store.models.User;
import da.store.service.BusinessService;
import da.store.service.impl.BusinessServiceImpl;

@WebServlet("/admin/OrderServlet")
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static BusinessService service = new BusinessServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
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

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null || service.isManager(user.getID()) == false) {
                request.setAttribute("message", "权限不足");
                request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
            } else {
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                List<Order> list = service.findOrder(status);
                request.setAttribute("list", list);
                request.getRequestDispatcher("/jsps/admin/bms-listorder.jsp").forward(request, response);
            }
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
            User user = (User) request.getSession().getAttribute("user");
            if (user == null || service.isManager(user.getID()) == false) {
                request.setAttribute("message", "权限不足");
                request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
            } else {
                String id = request.getParameter("id");
                Boolean status = Boolean.parseBoolean(request.getParameter("status"));
                service.updateOrder(id, status);
                request.setAttribute("message", "修改成功");
                request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "修改失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

}
