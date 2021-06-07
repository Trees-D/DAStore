package da.store.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import da.store.models.User;
import da.store.service.BusinessService;
import da.store.service.impl.BusinessServiceImpl;

@WebServlet("/client/LoginBMSServlet")
public class LoginBMSServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static BusinessService service = new BusinessServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null || service.isManager(user.getID()) == false) {
                request.setAttribute("message", "权限不足，无法进入后台");
                request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/jsps/admin/bms.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "权限不足，无法进入后台");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
}
