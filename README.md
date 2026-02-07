# Ứng dụng Quản lý Sản phẩm với Spring Boot Validation

## Mô tả
Ứng dụng web quản lý sản phẩm được xây dựng bằng Spring Boot với validation, theo đúng kiến trúc Model-Service-Controller.

## Tính năng chính

### Model
- **Product**: Quản lý thông tin sản phẩm
  - `@NotBlank`: Tên sản phẩm không được để trống  
  - `@Size(min=2, max=200)`: Tên hình ảnh không quá 200 ký tự
  - `@NotNull`, `@Min(1)`, `@Max(9999999)`: Giá sản phẩm từ 1-9999999
  
- **Category**: Quản lý danh mục sản phẩm
  - `@NotBlank`: Tên danh mục không được để trống

### Service  
- **ProductService**: Xử lý logic nghiệp vụ
  - `getAll()`: Lấy tất cả sản phẩm
  - `get(int id)`: Lấy sản phẩm theo ID
  - `add(Product)`: Thêm sản phẩm mới
  - `update(Product)`: Cập nhật sản phẩm
  - `updateImage()`: Upload và xử lý hình ảnh
  - `delete(int id)`: Xóa sản phẩm

### Controller
- **ProductController**: Xử lý các request HTTP
  - `GET /products`: Hiển thị danh sách sản phẩm
  - `GET /products/create`: Form thêm sản phẩm
  - `POST /products/store`: Lưu sản phẩm mới (có validation)
  - `GET /products/edit/{id}`: Form sửa sản phẩm
  - `POST /products/update/{id}`: Cập nhật sản phẩm
  - `GET /products/delete/{id}`: Xóa sản phẩm

## Cấu trúc thư mục
```
src/main/java/com/example/validation/
├── ValidationApplication.java      # Main class
├── controller/
│   └── ProductController.java      # Web controller
├── model/
│   ├── Product.java               # Entity sản phẩm
│   └── Category.java              # Entity danh mục
└── service/
    └── ProductService.java        # Business logic

src/main/resources/
├── application.properties         # Cấu hình ứng dụng
├── templates/                     # Thymeleaf templates
│   ├── index.html                # Danh sách sản phẩm
│   ├── create.html               # Form thêm mới
│   └── edit.html                 # Form chỉnh sửa
└── static/                       # Static files
    └── images/                   # Thư mục lưu ảnh
```

## Cách chạy ứng dụng

### Phương pháp 1: Với Maven (nếu có)
```bash
mvn spring-boot:run
```

### Phương pháp 2: Chạy trực tiếp bằng Java
```bash
# 1. Compile các file Java
javac -cp "lib/*" -d target src/main/java/com/example/validation/*.java src/main/java/com/example/validation/*/*.java

# 2. Chạy ứng dụng  
java -cp "target;lib/*" com.example.validation.ValidationApplication
```

### Phương pháp 3: Sử dụng IDE
1. Import project vào IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Đồng bộ dependencies 
3. Chạy class `ValidationApplication`

## Truy cập ứng dụng
- URL: http://localhost:8080/products
- Giao diện web với Bootstrap để quản lý sản phẩm

## Validation Rules
1. **Tên sản phẩm**: Không được để trống
2. **Giá**: Phải từ 1 đến 9,999,999 VNĐ
3. **Hình ảnh**: Tên file không quá 200 ký tự, chỉ nhận file ảnh
4. **Danh mục**: Điện thoại hoặc Laptop

## Tính năng Upload File
- Hỗ trợ upload hình ảnh sản phẩm
- Validation định dạng file (chỉ nhận image/*)
- Lưu trữ trong thư mục `static/images/`
- Tự động tạo tên file unique bằng UUID

## Demo Screenshots
1. **Trang danh sách**: Hiển thị tất cả sản phẩm với hình ảnh
2. **Form thêm**: Validation real-time với Bootstrap
3. **Form sửa**: Giữ nguyên hình cũ nếu không upload mới
4. **Xóa**: Confirm trước khi xóa

Dự án hoàn chỉnh theo đúng yêu cầu đề bài với validation đầy đủ!
