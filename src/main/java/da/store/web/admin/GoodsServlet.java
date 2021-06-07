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
import da.store.models.Goods;
import da.store.service.BusinessService;
import da.store.service.impl.BusinessServiceImpl;

@WebServlet("/admin/GoodsServlet")
public class GoodsServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private BusinessService service = new BusinessServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("selectCategory".equals(method)) {
            selectCategory(request, response);
        }
        if ("add".equals(method)) {
            add(request, response);
        }
        if ("getAll".equals(method)) {
            getAll(request, response);
        }
        if ("remove".equals(method)) {
            remove(request, response);
        }
        if ("modifyAll".equals(method)) {
            modifyAll(request, response);
        }
        if ("modify".equals(method)) {
            modify(request, response);
        }
        if ("modifyPage".equals(method)) {
            modifyPage(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void selectCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> list = service.getAllCategory();
            request.setAttribute("categories", list);
            request.getRequestDispatcher("/jsps/admin/bms-addgoods.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "请求失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }
    
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Goods goods = new Goods();
            goods.setID(UUID.randomUUID().toString());
            goods.setName(request.getParameter("name"));
            goods.setDescription(request.getParameter("description"));
            goods.setPrice(Double.parseDouble(request.getParameter("price")));
            String categoryID = request.getParameter("category_id");
            Category category = service.findCategory(categoryID);
            goods.setCategory(category);
            service.addGoods(goods);
            request.setAttribute("message", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "添加失败");
        }
        request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Goods> goodsList = service.getAllGoods();
            request.setAttribute("goodslist", goodsList);
            request.getRequestDispatcher("/jsps/admin/bms-listgoods.jsp").forward(request, response);;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "查询失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

    private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Goods goods = new Goods();
            goods.setID(request.getParameter("gid"));
            goods.setName(request.getParameter("name"));
            goods.setDescription(request.getParameter("description"));
            goods.setPrice(Double.parseDouble(request.getParameter("price")));
            String categoryID = request.getParameter("category_id");
            Category category = service.findCategory(categoryID);
            goods.setCategory(category);
            service.updateGoods(goods);
            modifyAll(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "查询失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

    private void modifyAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Goods> goodsList = service.getAllGoods();
            request.setAttribute("goodslist", goodsList);
            request.getRequestDispatcher("/jsps/admin/bms-modifylistgoods.jsp").forward(request, response);;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "查询失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

    private void modifyPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> list = service.getAllCategory();
            Goods goods = service.findGoods(request.getParameter("gid"));
            request.setAttribute("categories", list);
            request.setAttribute("modifyGoodsData", goods);
            request.getRequestDispatcher("/jsps/admin/bms-modifygoods.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "请求失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Goods goods = service.findGoods(request.getParameter("gid"));
            service.removeGoods(goods);
            getAll(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "删除失败");
            request.getRequestDispatcher("/jsps/message.jsp").forward(request, response);
        }
    }

}
