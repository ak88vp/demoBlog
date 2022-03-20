package TestMd3.controller;

import TestMd3.model.Category;
import TestMd3.model.Product;
import TestMd3.service.CategoryService;
import TestMd3.service.ProductService;
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
    ProductService productCategory;
    @Autowired
    CategoryService categoryService;
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
    public String getCreate(Product product) throws SQLException {
        productCategory.add(product);
        return "redirect:/products";
    }
    @GetMapping ("/delete")
    public String getDelete(@RequestParam int id) throws SQLException {
        productCategory.delete(id);
        return "redirect:/products";
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
    public String getEdit(@RequestParam int id,Product product) throws SQLException {
        productCategory.edit(id,product);
        return "redirect:/products";
    }
}
