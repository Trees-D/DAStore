package da.store.web.admin;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import da.store.models.User;
import da.store.service.BusinessService;
import da.store.service.impl.BusinessServiceImpl;

@WebServlet("/admin/UserServlet")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static BusinessService service = new BusinessServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("add".equals(method)) {
            add(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = new User();
            user.setID(UUID.randomUUID().toString());
            user.setName(request.getParameter("name"));
            user.setPassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email"));
            user.setAddress(request.getParameter("address"));
            service.addUser(user);
            if ("S".equals(request.getParameter("permission"))) {
                service.setManager(user);
            }
            request.setAttribute("message", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "添加失败");
        }
        request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
    }
}
