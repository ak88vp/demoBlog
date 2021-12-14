package TestMd3.controller;

import TestMd3.model.Category;
import TestMd3.model.Product;
import TestMd3.service.CategoryServiceImpl;
import TestMd3.service.ProductCategoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductCategoryImpl productCategory;
    @Autowired
    CategoryServiceImpl categoryService;
    @GetMapping("")
    public String call() {
        return "index";
    }

    @GetMapping ("/products")
    public ModelAndView showList(Model model) throws SQLException {
        ModelAndView modelAndView=new ModelAndView("/listProduct");
        List <Product> list=productCategory.printAll();
        List<Category> categoryList=allCategory(list);

        model.addAttribute("products",list);
        model.addAttribute("categorys",categoryList);

        return modelAndView;
    }
    private List<Category> allCategory(List<Product> list) throws SQLException {
        List<Category> list1=new ArrayList<>();
        for (Product product : list) {
            Category category = categoryService.findById(product.getIdCategory());
            list1.add(category);
        }

        return list1;
    }
    @GetMapping("/create")
    public ModelAndView showCreate(Model model) throws SQLException {
        ModelAndView modelAndView=new ModelAndView("/showCreate");
        List<Category> categoryList=categoryService.printAll();
        model.addAttribute("categorys",categoryList);

        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView getCreate(Model model,@RequestParam String name,int price,int quantity,String color,String description,int idCategory) throws SQLException {
        productCategory.add(new Product(name,price,quantity,color,description,idCategory));

        ModelAndView modelAndView=new ModelAndView("/listProduct");
        List <Product> list=productCategory.printAll();
        List<Category> categoryList=allCategory(list);

        model.addAttribute("products",list);
        model.addAttribute("categorys",categoryList);


        return modelAndView;
    }
    @GetMapping ("/delete")
    public ModelAndView getDelete(Model model,@RequestParam int id) throws SQLException {
        productCategory.delete(id);
        ModelAndView modelAndView=new ModelAndView("/listProduct");
        List <Product> list=productCategory.printAll();
        List<Category> categoryList=allCategory(list);
        model.addAttribute("products",list);
        model.addAttribute("categorys",categoryList);
        return modelAndView;
    }
    @GetMapping("edit")
    public ModelAndView showEdit(Model model,@RequestParam int id) throws SQLException {
        ModelAndView modelAndView=new ModelAndView("/showEdit");
        Product product=productCategory.findById(id);
        List<Category> categoryList=categoryService.printAll();
        model.addAttribute("product",product);
        model.addAttribute("categorys",categoryList);

        return modelAndView;
    }
    @PostMapping("edit")
    public ModelAndView getEdit(Model model,@RequestParam String name,int price,int quantity,String color,String description,int idCategory,int id) throws SQLException {
        ModelAndView modelAndView=new ModelAndView("/listProduct");
        productCategory.edit(id,new Product(name,price,quantity,color,description,idCategory));

        List <Product> list=productCategory.printAll();
        List<Category> categoryList=allCategory(list);
        model.addAttribute("products",list);
        model.addAttribute("categorys",categoryList);

        return modelAndView;
    }
}
