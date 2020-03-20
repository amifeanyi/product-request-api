package com.product.api.repositories;

import java.util.Optional;

import com.product.api.domain.RequestDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RequestRepository extends PagingAndSortingRepository<RequestDomain, Integer>{

    Optional<RequestDomain> findById(Integer requestId);

    Page<RequestDomain> findAll(Pageable pageable);

}