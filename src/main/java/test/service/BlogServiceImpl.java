package test.service;

import jdk.nashorn.internal.ir.RuntimeNode;
import test.model.Blog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogServiceImpl implements BlogService{
    public BlogServiceImpl() {
    }

    protected Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogg?useSSL=false", "root", "123456");
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public List<Blog> printAll() throws SQLException {
        List<Blog> blogs=new ArrayList<>();
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select *from blog");
        ResultSet rs=preparedStatement.executeQuery();

        while (rs.next()){
            int id=rs.getInt("id") ;
            String title=rs.getString("title") ;
            String content=rs.getString("Content") ;
            int idCategory=rs.getInt("idCategory") ;
            blogs.add(new Blog(id,title,content,idCategory));

        }

        return blogs;
    }

    @Override
    public List<Blog> printAllIdCategory() throws SQLException {
        return null;
    }

    @Override
    public List<Blog> findByName(String name) throws SQLException {

        return null;
    }

    @Override
    public Blog findById(int id) throws SQLException {
        Blog blog=null;
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select *from blog where id=?");
        preparedStatement.setInt(1,id);
        ResultSet rs=preparedStatement.executeQuery();
        while (rs.next()){
            int id1=rs.getInt("id");
            String tile=rs.getString("title");
            String content=rs.getString("Content");
            int idCategory=rs.getInt("idCategory");
            blog=new Blog(id1,tile,content,idCategory);
        }
        return blog;
    }

    @Override
    public void add(Blog blog) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("insert into blog (title, Content, idCategory) value (?,?,?)");
        preparedStatement.setString(1,blog.getName());
        preparedStatement.setString(2,blog.getContent());
        preparedStatement.setInt(3,blog.getIdCategory());
        preparedStatement.executeUpdate();
    }
    @Override
    public void edit(Blog blog) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("update blog set title=?, Content=?, idCategory=? where id=?");
        preparedStatement.setString(1,blog.getName());
        preparedStatement.setString(2,blog.getContent());
        preparedStatement.setInt(3,blog.getIdCategory());
        preparedStatement.setInt(4,blog.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("delete from blog where id=?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();

    }
}
