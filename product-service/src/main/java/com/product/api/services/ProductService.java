package com.product.api.services;

import java.util.List;
import com.seyi.api.model.ProductDto;


public interface ProductService{

    public List<ProductDto> getProductRequests(Long pageNo, Long pageSize);

    public ProductDto getProductRequestById(Integer productId);

    public Integer createProductRequest(ProductDto product);

}