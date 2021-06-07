package da.store.web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import da.store.models.Category;
import da.store.models.Goods;
import da.store.models.User;
import da.store.models.query.QueryHeader;
import da.store.service.BusinessService;
import da.store.service.impl.BusinessServiceImpl;

@WebServlet("/client/IndexServlet")
public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static BusinessService service = new BusinessServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String category_id = request.getParameter("category_id");
            List<Goods> goodslist = null;
            if (category_id != null && category_id.trim().equals("") == false) {
                QueryHeader header = new QueryHeader();
                header.setQueryName("category_id");
                header.setQueryValue(category_id);
                goodslist = service.goodsQuery(header);
            }
            else {
                User user = (User) request.getSession().getAttribute("user");
                goodslist = service.recommend(user);
            }
            List<Category> categories = service.getAllCategory();
            request.setAttribute("preload_goodslist", goodslist);
            request.setAttribute("preload_categories", categories);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "信息加载失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
