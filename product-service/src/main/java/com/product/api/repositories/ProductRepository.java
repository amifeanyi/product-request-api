package com.product.api.repositories;

import java.util.Optional;

import com.product.api.domain.ProductDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ProductRepository extends PagingAndSortingRepository<ProductDomain, Integer>{

    Optional<ProductDomain> findById(Integer requestId);

    Page<ProductDomain> findAll(Pageable pageable);

}