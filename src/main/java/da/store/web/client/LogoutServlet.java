package da.store.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import da.store.service.BusinessService;
import da.store.service.impl.BusinessServiceImpl;

@WebServlet("/client/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    BusinessService service = new BusinessServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute("cart");
                session.removeAttribute("user");
            }
            request.getRequestDispatcher("/jsps/client/login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "注销失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
}
