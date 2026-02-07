package com.example.validation.controller;

import com.example.validation.model.Product;
import com.example.validation.model.Category;
import com.example.validation.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", productService.getAll());
        return "index";
    }
    
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }
    
    @PostMapping("/store")
    public String store(@Valid @ModelAttribute Product product, 
                       BindingResult result,
                       @RequestParam("imageProduct") MultipartFile imageProduct,
                       @RequestParam(value = "categoryName", required = false) String categoryName,
                       Model model) {
        
        // Tạo Category object từ categoryName
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            Category category = new Category();
            category.setName(categoryName);
            product.setCategory(category);
        }
        
        if (result.hasErrors()) {
            return "create";
        }
        
        try {
            productService.updateImage(product, imageProduct);
            productService.add(product);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "create";
        }
        
        return "redirect:/products";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Product product = productService.get(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "edit";
    }
    
    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,
                        @Valid @ModelAttribute Product product,
                        BindingResult result,
                        @RequestParam("imageProduct") MultipartFile imageProduct,
                        Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "edit";
        }
        
        product.setId(id);
        try {
            if (!imageProduct.isEmpty()) {
                productService.updateImage(product, imageProduct);
            }
            productService.update(product);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "edit";
        }
        
        return "redirect:/products";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }
}