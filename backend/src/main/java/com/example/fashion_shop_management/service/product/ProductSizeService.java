package com.example.fashion_shop_management.service.product;

import com.example.fashion_shop_management.constant.Size;
import com.example.fashion_shop_management.entity.Product;
import com.example.fashion_shop_management.entity.ProductSize;
import com.example.fashion_shop_management.repository.ProductSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductSizeService {
    private final ProductSizeRepository productSizeRepository;

    public ProductSize createProductSize(ProductSize productSize) {
        return productSizeRepository.save(productSize);
    }

    public List<ProductSize> getAll() {
        return productSizeRepository.findAll();
    }

    public Optional<ProductSize> getProductSize(Integer productSizeId) {
        return productSizeRepository.findById(productSizeId);
    }

    public void updateProductSize(ProductSize productSize) {
        Size size = productSize.getSize();

        // Tìm ProductSize theo product và size
        Optional<ProductSize> existingProductSizeOpt = productSizeRepository.findByProductAndSize(productSize.getProduct(), size);

        // Nếu đã tồn tại, cập nhật số lượng tồn kho và lưu lại
        if (existingProductSizeOpt.isPresent()) {
            ProductSize existingProductSize = existingProductSizeOpt.get();
            existingProductSize.setStock(productSize.getStock());
            productSizeRepository.save(existingProductSize);
        } else {
            // Nếu không tìm thấy, tạo mới ProductSize và lưu
            productSizeRepository.save(productSize);
        }
    }

    public void deleteProductSize(Integer productSizeId) {
        productSizeRepository.deleteById(productSizeId);
    }

    public void deleteProductSizesByProductId(Integer productId) {
        List<ProductSize> productSizes = productSizeRepository.findByProductId(productId);
        productSizeRepository.deleteAll(productSizes);
    }

    public List<ProductSize> getAllByProductId(Integer productId) {
        return productSizeRepository.findByProductId(productId);
    }
}
