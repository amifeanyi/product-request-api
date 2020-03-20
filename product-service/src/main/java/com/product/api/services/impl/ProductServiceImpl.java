package com.product.api.services.impl;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.product.api.exceptions.ProductNotFoundException;
import com.product.api.mapper.ProductMapper;
import com.product.api.mapper.RequestMapper;
import com.product.api.domain.ProductDomain;
import com.product.api.domain.RequestDomain;
import com.product.api.repositories.ProductRepository;
import com.product.api.repositories.RequestRepository;
import com.product.api.services.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.seyi.api.model.ProductDto;
import com.seyi.api.model.ProductRequestDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final RequestRepository requestRepository;
    private final ProductMapper productMapper;
    private final RequestMapper requestMapper;

    @Override
    public List<ProductDto> getProductRequests(Long pageNo, Long pageSize) {
        final PageRequest pageRequest = PageRequest.of(Math.toIntExact(pageNo), Math.toIntExact(pageSize),
                Sort.by("name"));
        final Page<ProductDomain> pagedResult = productRepository.findAll(pageRequest);

        if (pagedResult.hasContent())
            return productMapper.toDto(pagedResult.getContent());
        else
            return Collections.emptyList();
    }

    @Override
    public ProductDto getProductRequestById(Integer requestId) {
        Optional<ProductDomain> optionalProduct = productRepository.findById(requestId);
        ProductDto productDto = optionalProduct.map(p -> productMapper.toDto(p))
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + requestId + " not found."));

        List<ProductRequestDto> productRequestDtos = optionalProduct.map(p -> requestMapper.toDto(p.getRequestDomain()))
                .orElseThrow(() -> new ProductNotFoundException("Request not found."));

        productDto.setProductRequestDto(productRequestDtos);

        return productDto;

    }

    @Override
    public Integer createProductRequest(ProductDto productDto) {
        final ProductDomain productDomain = productMapper.toEntity(productDto);
        log.info("request entity {}", productDomain.getRequestDomain().toArray().toString());

        ProductDomain savedProduct = productRepository.save(productDomain);

        final List<RequestDomain> requestDomains = new ArrayList<>();

        productDto.getProductRequestDto().forEach(productRequestDto -> {
            final RequestDomain requestDomain = requestMapper.toEntity(productRequestDto);
            requestDomain.setProductDomain(savedProduct);
            requestDomain.setCreatedDate(OffsetDateTime.now());
            requestDomains.add(requestDomain);
        });

        savedProduct.setRequestDomain(requestDomains);
        requestRepository.saveAll(requestDomains);
        return savedProduct.getId();
    }
}