package com.product.api.mapper;


import com.product.api.domain.ProductDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.seyi.api.model.ProductDto;

@Mapper(componentModel="spring", uses = {RequestMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper extends IEntityMapper<ProductDto, ProductDomain>{

    @Mapping(source = "id", target = "productId")
    ProductDto toDto(final ProductDomain productDomain);

    @Mapping(source = "productId", target = "id")
    ProductDomain toEntity(final ProductDto productDto);

    default ProductDomain fromId(final Integer id) {

        if (id == null) {
            return null;
        }

        final ProductDomain productDomain=new ProductDomain();

        productDomain.setId(id);

        return productDomain;
    }


}