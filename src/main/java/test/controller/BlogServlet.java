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
                try {
                    showCreate(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    deleteBlog(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    showEdit(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            default:
                try {
                    showBlog(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("showEdit.jsp");
        int id= Integer.parseInt(request.getParameter("id"));
        Blog blog=blogService.findById(id);
        request.setAttribute("blog",blog);
        requestDispatcher.forward(request,response);

    }

    private void deleteBlog(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        blogService.delete(id);
        response.sendRedirect("/blogs");
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("showCreate.jsp");

        List<Category> list1= categoryService.printAll();

        request.setAttribute("categorys",list1);
       requestDispatcher.forward(request,response);
    }

    private void showBlog(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("showBlog.jsp");
        List<Blog> blogList=blogService.printAll();
        List<Category> categoryList= allCategory(blogList);
        request.setAttribute("blogs",blogList);
        request.setAttribute("categorys",categoryList);
        requestDispatcher.forward(request,response);

    }
    protected List<Category> allCategory(List<Blog> list) throws SQLException {
        List<Category> categoryList=new ArrayList<>();
        for (Blog blog:list) {
            Category category=categoryService.findById(blog.getIdCategory());
            categoryList.add(category);
        }
        return categoryList;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "create":
                try {
                    createBlog(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;}
    }

    private void createBlog(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idCategory= Integer.parseInt(request.getParameter("idCategory"));
        String title=request.getParameter("title");
        String content=request.getParameter("content");

        blogService.add(new Blog(title,content,idCategory));
        response.sendRedirect("/blogs");
    }
}
