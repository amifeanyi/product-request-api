package com.product.api.mapper;
import com.seyi.api.model.ProductRequestDto;
import java.util.List;
import com.product.api.domain.RequestDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface RequestMapper extends IEntityMapper<ProductRequestDto, RequestDomain>{

    ProductRequestDto toDto(final RequestDomain requestDomain);

    List<ProductRequestDto> toDto(final List<RequestDomain> requestDomains);

    RequestDomain toEntity(final ProductRequestDto productRequestDto);

    List<RequestDomain> toEntity(final List <ProductRequestDto> productDtos);

    default RequestDomain fromId(final Integer id) {

        if (id == null) {
            return null;
        }

        final RequestDomain requestDomain=new RequestDomain();
        requestDomain.setRequestId(id);

        return requestDomain;
    }

}