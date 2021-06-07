package da.store.web.client;

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

@WebServlet("/client/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static BusinessService service = new BusinessServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User user = new User();
            user.setID(UUID.randomUUID().toString());
            user.setName(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email"));
            user.setAddress(request.getParameter("address"));
            if (user.getID() != null && user.getName() != null && user.getPassword() != null && user.getEmail() != null
                    && user.getAddress() != null) {
                service.addUser(user);
                request.getRequestDispatcher("/jsps/client/login.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "注册失败");
                request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "注册失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
