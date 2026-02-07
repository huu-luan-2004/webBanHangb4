package com.example.validation.service;

import com.example.validation.model.Product;
import com.example.validation.model.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class ProductService {
    List<Product> listProduct = new ArrayList<>();
    
    // Constructor để thêm sẵn sản phẩm mẫu
    public ProductService() {
        // Sản phẩm 1: iPhone 15
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("iPhone 15");
        product1.setPrice(25000000L);
        product1.setImage("https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-512gb-xanh-la-thumb-600x600.jpg");
        
        Category category1 = new Category();
        category1.setId(1);
        category1.setName("điện thoại");
        product1.setCategory(category1);
        
        listProduct.add(product1);
        
        // Sản phẩm 2: MacBook Pro
        Product product2 = new Product();
        product2.setId(2);
        product2.setName("MacBook Pro M3");
        product2.setPrice(45000000L);
        product2.setImage("https://cdn.tgdd.vn/Products/Images/44/309013/macbook-pro-14-inch-m3-2023-xam-thumb-600x600.jpg");
        
        Category category2 = new Category();
        category2.setId(2);
        category2.setName("laptop");
        product2.setCategory(category2);
        
        listProduct.add(product2);
        
        // Sản phẩm 3: Samsung Galaxy S24
        Product product3 = new Product();
        product3.setId(3);
        product3.setName("Samsung Galaxy S24 Ultra");
        product3.setPrice(30000000L);
        product3.setImage("https://cdn.tgdd.vn/Products/Images/42/307174/samsung-galaxy-s24-ultra-xam-thumb-600x600.jpg");
        
        Category category3 = new Category();
        category3.setId(1);
        category3.setName("điện thoại");
        product3.setCategory(category3);
        
        listProduct.add(product3);
        
        // Sản phẩm 4: Dell XPS 13
        Product product4 = new Product();
        product4.setId(4);
        product4.setName("Dell XPS 13");
        product4.setPrice(35000000L);
        product4.setImage("https://cdn.tgdd.vn/Products/Images/44/226433/dell-xps-13-9310-i7-1165g7-den-thumb-600x600.jpg");
        
        Category category4 = new Category();
        category4.setId(2);
        category4.setName("laptop");
        product4.setCategory(category4);
        
        listProduct.add(product4);
        
        // Sản phẩm 5: iPad Pro
        Product product5 = new Product();
        product5.setId(5);
        product5.setName("iPad Pro 12.9 inch");
        product5.setPrice(28000000L);
        product5.setImage("https://cdn.tgdd.vn/Products/Images/522/325531/ipad-pro-m4-13-inch-wifi-xam-thumb-600x600.jpg");
        
        Category category5 = new Category();
        category5.setId(3);
        category5.setName("tablet");
        product5.setCategory(category5);
        
        listProduct.add(product5);
    }
    
    public List<Product> getAll() {
        return listProduct;
    }
    
    public Product get(int id) {
        return listProduct.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    
    public void add(Product newProduct) {
        int maxId = listProduct.stream().mapToInt(Product::getId).max().orElse(0);
        newProduct.setId(maxId + 1);
        listProduct.add(newProduct);
    }
    
    public void update(Product editProduct) {
        Product find = get(editProduct.getId());
        if (find != null) {
            find.setPrice(editProduct.getPrice());
            find.setName(editProduct.getName());
            if (editProduct.getImage() != null)
                find.setImage(editProduct.getImage());
        }
    }
    
    public void updateImage(Product newProduct, MultipartFile imageProduct) {
        if (imageProduct != null && !imageProduct.isEmpty()) {
            String contentType = imageProduct.getContentType();
            if (contentType != null && !contentType.startsWith("image/")) {
                throw new IllegalArgumentException("Tệp tải lên không phải là hình ảnh!");
            }
            
            try {
                Path dirImages = Paths.get("static/images");
                if (!Files.exists(dirImages)) {
                    Files.createDirectories(dirImages);
                }
                String newFileName = UUID.randomUUID() + "_" + imageProduct.getOriginalFilename();
                Path pathFileUpload = dirImages.resolve(newFileName);
                Files.copy(imageProduct.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);
                newProduct.setImage(newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void delete(int id) {
        listProduct.removeIf(p -> p.getId() == id);
    }
}