package com.example.validation.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter  
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @Size(min = 2, max = 200, message = "Tên hình ảnh không quá 200 ký tự")
    private String image;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 1, message = "Giá sản phẩm không được nhỏ hơn 1")
    @Max(value = 9999999, message = "Giá sản phẩm không được lớn hơn 9999999")
    private Long price;

    private Category category;
}