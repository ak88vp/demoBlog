package TestMd3.service;

import TestMd3.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService{
    public CategoryServiceImpl() {
    }

    protected Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/products?useSSL=false", "root", "123456");
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Category> printAll() throws SQLException {
        List<Category> categoryList=new ArrayList<>();
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select *from category");
        ResultSet rs=preparedStatement.executeQuery();
        while (rs.next()){
            int id=rs.getInt("id") ;
            String name=rs.getString("name") ;

            categoryList.add(new Category(id,name));

        }

        return categoryList;
    }

    @Override
    public List<Category> printAllIdCategory() throws SQLException {
        return null;
    }

    @Override
    public List<Category> findByName(String name) throws SQLException {
        return null;
    }

    @Override
    public Category findById(int id) throws SQLException {
        Category category=null;
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select *from category where id=?");
        preparedStatement.setInt(1,id);
        ResultSet rs=preparedStatement.executeQuery();
        while (rs.next()){
            int id1=rs.getInt("id");
            String name=rs.getString("name");

            category=new Category(id1,name);
        }
        return category;
    }

    @Override
    public void add(Category category) throws SQLException {

    }

    @Override
    public void edit(int id, Category category) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
