
package com.product.api.controller;

import java.util.List;

import com.product.api.services.ProductService;
import com.seyi.api.product.ApiApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.seyi.api.model.ProductDto;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class ProductController implements ApiApi{

    private final ProductService productService;

    @Override
    public ResponseEntity<ProductDto> getProductByRequestId(Integer requestId){
        return ResponseEntity.ok(productService.getProductRequestById(requestId));
    }

    @Override
    public ResponseEntity<List<ProductDto>> getProductRequests(Long pageNo, Long pageSize)  {
        return ResponseEntity.ok(productService.getProductRequests(pageNo, pageSize));
    }

    @Override
    public ResponseEntity<Void> submitProductRequest(ProductDto body)  {
        productService.createProductRequest(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}