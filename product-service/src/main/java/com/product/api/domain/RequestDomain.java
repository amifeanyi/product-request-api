package com.product.api.domain;

import java.time.OffsetDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_request")
@Data
public class RequestDomain{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int requestId;

    @ManyToOne
	@JoinColumn
    private ProductDomain productDomain;
    

    private OffsetDateTime createdDate;
    
}