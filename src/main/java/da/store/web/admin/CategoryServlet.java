package da.store.web.admin;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import da.store.models.Category;
import da.store.service.BusinessService;
import da.store.service.impl.BusinessServiceImpl;

@WebServlet("/admin/CategoryServlet")
public class CategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BusinessService service = new BusinessServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("add".equals(method)) {
            add(request, response);
        }
        if ("getAll".equals(method)) {
            getAll(request, response);
        }
        if ("remove".equals(method)) {
            remove(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Category category = new Category();
            category.setName(request.getParameter("name"));
            category.setDescription(request.getParameter("description"));
            category.setID(UUID.randomUUID().toString());
            service.addCategory(category);
            request.setAttribute("message", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "添加失败");
        }
        request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> list = service.getAllCategory();
            request.setAttribute("categories", list);
            request.getRequestDispatcher("/jsps/admin/bms-listcategory.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "查询失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Category category = service.findCategory(request.getParameter("cid"));
            service.removeCategory(category);
            getAll(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "无法删除，请检查该分类下是否有商品");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

}
