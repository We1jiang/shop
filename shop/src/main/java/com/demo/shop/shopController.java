package com.demo.shop;

import com.demo.shop.product.domain.Product;
import com.demo.shop.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/shop")
public class shopController {

    private final ProductService productService;

    @Autowired
    public shopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('product:read')")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('product:write')")
    public void addNewProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @DeleteMapping("{productID}")
    @PreAuthorize("hasAuthority('product:write')")
    public void deleteProduct(@PathVariable("productID") Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("{productID}")
    @PreAuthorize("hasAuthority('product:write')")
    public void updateProduct(
            @PathVariable("productID") long productID,
            @RequestParam Product product) {

        productService.updateProduct(productID, product);

    }
}
