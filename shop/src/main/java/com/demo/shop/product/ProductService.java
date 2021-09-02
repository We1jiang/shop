package com.demo.shop.product;

import com.demo.shop.product.domain.Product;
import com.demo.shop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        Optional<Product> productByCostCode = productRepository.findProductByCostCode(product.getCostCode());

        if (productByCostCode.isPresent()) {
            throw new IllegalStateException("costCode existed");
        }

        productRepository.save(product);
    }

    public void deleteProduct(Long productID) {
        boolean exists = productRepository.existsById(productID);

        if (!exists) {
            throw new IllegalStateException("product" + productID + "does not exists");
        }

        productRepository.deleteById(productID);

    }

    public void updateProduct(long productID, Product product) {
        Product target = productRepository.findById(productID)
                .orElseThrow(() -> new IllegalStateException("product" + productID + "does not exists"));

        target.setName(product.getName());
        target.setCompany(product.getCompany());
        target.setPvpPrice(product.getPvpPrice());
        target.setCostPrice(product.getCostPrice());
        target.setPvpCode(product.getPvpCode());
        target.setCostCode(product.getCostCode());
    }
}
