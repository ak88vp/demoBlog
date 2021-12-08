package test.controller;

import test.model.Blog;
import test.model.Category;
import test.service.BlogService;
import test.service.BlogServiceImpl;
import test.service.CategoryService;
import test.service.CategoryServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "test.controller.BlogServlet", value = "/blogs")
public class BlogServlet extends HttpServlet {
    BlogService blogService=new BlogServiceImpl();
    CategoryService categoryService=new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "create":
                showCreate(request,response);
                break;

            default:
                try {
                    showBlog(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("showCreate.jsp");
       requestDispatcher.forward(request,response);
    }

    private void showBlog(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("showBlog.jsp");
        List<Blog> blogList=blogService.printAll();
        List<Category> categoryList=allBlog(blogList);
        request.setAttribute("blogs",blogList);
        request.setAttribute("categorys",categoryList);
        requestDispatcher.forward(request,response);

    }
    protected List<Category> allBlog( List<Blog> list) throws SQLException {
        List<Category> categoryList=new ArrayList<>();
        for (Blog blog:list) {
            Category category=categoryService.findById(blog.getIdCategory());
            categoryList.add(category);
        }
        return categoryList;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
