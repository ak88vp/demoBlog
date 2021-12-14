package TestMd3.service;

import TestMd3.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryImpl implements ProductService {
    public ProductCategoryImpl() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/products?useSSL=false", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Product> printAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select *from product");
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            int quantity = rs.getInt("quantity");
            String color = rs.getString("color");
            String description = rs.getString("description");
            int idCategory = rs.getInt("idCategory");
            products.add(new Product(id,name,price,quantity,color,description,idCategory));

        }

        return products;
    }

    @Override
    public List<Product> printAllIdCategory() throws SQLException {
        return null;
    }

    @Override
    public List<Product> findByName(String name) throws SQLException {
        List<Product> productList=new ArrayList<>();
        Connection connection=getConnection();
        PreparedStatement preparedStatement =connection.prepareStatement("select *from product where name like ?");
        preparedStatement.setString(1,'%'+name+'%');
        ResultSet rs=preparedStatement.executeQuery();
        while (rs.next()){
            int id=rs.getInt("id");
            String name1=rs.getString("name");
            int price=rs.getInt("price");
            int quantity=rs.getInt("quantity");
            String color=rs.getString("color");
            String description=rs.getString("description");
            int idCategory=rs.getInt("idCategory");
            productList.add(new Product(id,name1,price,quantity,color,description,idCategory));

        }
        return productList;
    }

    @Override
    public Product findById(int id) throws SQLException {
        Product product=null;
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select *from product where id=?");
        preparedStatement.setInt(1,id);
        ResultSet rs=preparedStatement.executeQuery();
        while (rs.next()){
            int id1= rs.getInt("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            int quantity = rs.getInt("quantity");
            String color = rs.getString("color");
            String description = rs.getString("description");
            int idCategory = rs.getInt("idCategory");
            product=new Product(id1,name,price,quantity,color,description,idCategory);
        }
        return product;
    }

    @Override
    public void add(Product product) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("insert into product (name,price,quantity,color,description,idCategory) value (?,?,?,?,?,?)");
    preparedStatement.setString(1,product.getName());
    preparedStatement.setInt(2,product.getPrice());
    preparedStatement.setInt(3,product.getQuantity());
    preparedStatement.setString(4,product.getColor());
    preparedStatement.setString(5,product.getDescription());
    preparedStatement.setInt(6,product.getIdCategory());
        preparedStatement.executeUpdate();
    }

    @Override
    public void edit(int id, Product product) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("update product set name=?,price=?,quantity=?,color=?,description=?,idCategory=? where id=?");

        preparedStatement.setString(1,product.getName());
        preparedStatement.setInt(2,product.getPrice());
        preparedStatement.setInt(3,product.getQuantity());
        preparedStatement.setString(4,product.getColor());
        preparedStatement.setString(5,product.getDescription());
        preparedStatement.setInt(6,product.getIdCategory());

        preparedStatement.setInt(7,id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("delete from product where id=?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();

    }
}
