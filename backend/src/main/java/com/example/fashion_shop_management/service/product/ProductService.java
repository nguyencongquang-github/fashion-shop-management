package com.example.fashion_shop_management.service.product;

import com.example.fashion_shop_management.constant.Size;
import com.example.fashion_shop_management.entity.Category;
import com.example.fashion_shop_management.entity.Product;
import com.example.fashion_shop_management.entity.ProductSize;
import com.example.fashion_shop_management.exception.NotFoundException;
import com.example.fashion_shop_management.mapper.ProductMapper;
import com.example.fashion_shop_management.repository.CategoryRepository;
import com.example.fashion_shop_management.repository.ProductRepository;
import com.example.fashion_shop_management.service.other.aws.AwsS3ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final AwsS3ServiceImpl awsS3Service;
    private final ProductSizeService productSizeService;
    private final CategoryRepository categoryRepository;


    // TODO: Implement createProduct method
    public Product createProduct(Integer categoryId, String name, String description,
                                 MultipartFile imageUrl, BigDecimal importPrice, BigDecimal retailPrice,
                                 List<String> sizes, Integer quantity) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found"));
        String productImageUrl = awsS3Service. saveImageToS3(imageUrl);

        Product product = new Product();
        product.setCategory(category);
        product.setName(name);
        product.setDescription(description);
        product.setImageUrl(productImageUrl);
        product.setImportPrice(importPrice);
        product.setRetailPrice(retailPrice);
        product = productRepository.save(product);


        for (int i = 0; i < sizes.size(); i++) {
            Size size = Size.valueOf(sizes.get(i));
            ProductSize productSize = ProductSize.builder()
                    .product(product)
                    .size(size)
                    .stock(quantity)
                    .quantity_sold(0)
                    .build();
            productSizeService.createProductSize(productSize);
        }

        product.setProductSizes(productSizeService.getAllByProductId(product.getId()));
        product = productRepository.save(product);
        return product;
    }

    // TODO: Implement updateProduct method
    public Product updateProduct(Integer productId, Integer categoryId, String name, String description,
                                 MultipartFile imageUrl, BigDecimal importPrice, BigDecimal retailPrice,
                                 List<String> sizes, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        product.setCategory(category);
        product.setName(name);
        product.setDescription(description);
        product.setImportPrice(importPrice);
        product.setRetailPrice(retailPrice);

        // Cập nhật hình ảnh nếu có thay đổi
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String productImageUrl = awsS3Service.saveImageToS3(imageUrl);
            product.setImageUrl(productImageUrl);
        }

        // Lấy danh sách kích thước hiện tại của sản phẩm
        List<Size> sizeList = product.getProductSizes().stream()
                .map(ProductSize::getSize).toList();

        // Cập nhật các kích thước và số lượng
        for (int i = 0; i < sizes.size(); i++) {
            Size size = Size.valueOf(sizes.get(i));

            // Nếu kích thước chưa có, tạo mới ProductSize
            if (!sizeList.contains(size)) {
                ProductSize productSize = ProductSize.builder()
                        .product(product)
                        .size(size)
                        .stock(quantity)
                        .build();
                productSizeService.createProductSize(productSize);
            }

            // Nếu kích thước đã có, cập nhật số lượng tồn kho
            ProductSize productSize = ProductSize.builder()
                    .product(product)
                    .size(size)
                    .stock(quantity)
                    .build();
            productSizeService.updateProductSize(productSize);
        }

        product.setProductSizes(productSizeService.getAllByProductId(product.getId()));  // Cập nhật danh sách ProductSize

        return productRepository.save(product);  // Lưu sản phẩm đã cập nhật
    }


    // TODO: Implement deleteProduct method
    public void deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        // Xóa các ProductSize liên quan đến sản phẩm
        productSizeService.deleteProductSize(product.getId());

        // Xóa hình ảnh sản phẩm từ S3 nếu cần
        if (product.getImageUrl() != null) {
//            awsS3Service.deleteImageFromS3(product.getImageUrl());
            product.setImageUrl(null);
        }

        productRepository.delete(product);  // Xóa sản phẩm
    }

    // TODO: Implement getProductById method
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    // TODO: Implement getAllProducts method
    public Map<String, Object> getAllProducts(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAllWithSizes(pageable);

        List<Map<String, Object>> products = productPage.stream().map(product -> {
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("productId", product.getId());
            productMap.put("name", product.getName());
            productMap.put("description", product.getDescription());
            productMap.put("import_price", product.getImportPrice());
            productMap.put("retail_price", product.getRetailPrice());
            productMap.put("category", product.getCategory().getName());
            productMap.put("imageUrl", product.getImageUrl());
            return productMap;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("content", products);
        response.put("totalPages", productPage.getTotalPages());
        response.put("totalElements", productPage.getTotalElements());

        return response;
    }


    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingOrDescriptionContaining(keyword, keyword);
    }

    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }
}







//    public List<Product> getAllProducts() {
//        return productRepository.findAll().stream().map(productMapper::toProductDto).toList();
//    }
//
//    public Product getProductInfo(Integer productId) {
//        return productRepository.findById(productId).map(productMapper::toProductDto).orElseThrow(() -> new NotFoundException("Product not found"));
//    }
//
//    public void deleteProduct(Integer productId) {
//        if (!productRepository.existsById(productId)) {
//            throw new NotFoundException("Product not found");
//        }
//        productRepository.deleteById(productId);
//        return !productRepository.existsById(productId);
//    }
//
//    public ProductDto updateProduct(Integer productId, ProductDto productDto) {
//        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
//
//        Category category = null;
//        String productImageUrl = null;
////
////        if (productDto.getCategoryId() != null) {
////            category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found"));
////        }
//
//        if (productDto.getImage() != null) {
//            productImageUrl = awsS3Service.saveImageToS3(productDto.getImage());
//        }
//
//        if (category != null) {
//            product.setCategory(category);
//        }
//
//        if (productImageUrl != null) product.setImageUrl(productImageUrl);
//        if (productDto.getName() != null) product.setName(productDto.getName());
//        if (productDto.getDescription() != null) product.setDescription(productDto.getDescription());
//        if (productDto.getImportPrice() != null) product.setImportPrice(productDto.getImportPrice());
//        if (productDto.getRetailPrice() != null) product.setRetailPrice(productDto.getRetailPrice());
//
//        return productMapper.toProductDto(productRepository.save(product));
//
//    }
//
//    public ProductDto addProduct(Integer categoryId, String name, String description,
//                                 BigDecimal importPrice, BigDecimal retailPrice,
//                                 MultipartFile image, Integer quantity,
//                                 String color, String size) {
//        String productImageUrl = awsS3Service.saveImageToS3(productDto.getImage());
//        Product product = productMapper.toProduct(productDto);
//        product.setImageUrl(productImageUrl);
//        return productMapper.toProductDto(productRepository.save(product));
//    }
//
//    public List<ProductDto> getProductsByCategory(Integer categoryId) {
//        return productRepository.findAllByCategoryId(categoryId).stream().map(productMapper::toProductDto).toList();
//    }
//
//    public List<ProductDto> searchProduct(String searchValue) {
//        List<Product> products = productRepository.findByNameContainingOrDescriptionContaining(searchValue, searchValue);
//
//        if (products.isEmpty()) {
//            throw new NotFoundException("No products found");
//        }
//
//        return products.stream().map(productMapper::toProductDto).toList();
//    }

