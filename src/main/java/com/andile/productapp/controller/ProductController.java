package com.andile.productapp.controller;

import com.andile.productapp.dto.ProductDto;
import com.andile.productapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<ProductDto> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProductDto> getProduct(@PathVariable String id){
        return productService.getProduct(id);
    }
//    @GetMapping("/product-range")
//    public Flux<ProductDto> getProductInRange(@RequestParam("min") double min,@RequestParam("max") double max){
//        return productService.getProductInRange(min,max);
//    }
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono){
        return productService.saveProduct(productDtoMono);
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono,@PathVariable String id){
        return productService.updateProduct(productDtoMono,id);
    }
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id);
    }
}
