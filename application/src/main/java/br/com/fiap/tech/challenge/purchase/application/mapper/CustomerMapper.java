package br.com.fiap.tech.challenge.purchase.application.mapper;

import br.com.fiap.tech.challenge.purchase.application.dto.FullCustomerDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.SimpleCustomerDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.purchase.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.purchase.enterprise.valueobject.EmailRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommonMapper.class})
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "name", expression = "java(source.name())")
    @Mapping(target = "email", expression = "java(source.toEmail())")
    @Mapping(target = "document", expression = "java(source.toDocument())")
    @Mapping(target = "enabled", expression = "java(source.enabled())")
    FullCustomerDTO toDTO(Customer source);

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "name", expression = "java(source.name())")
    SimpleCustomerDTO toSimpleDTO(Customer source);

    @Mapping(target = "email", source = "source", qualifiedByName = "getEmail")
    @Mapping(target = "document", source = "source", qualifiedByName = "getDocument")
    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    Customer toDomain(FullCustomerDTO source);

    @Named("getEmail")
    static EmailRegistration getEmail(FullCustomerDTO source) {
        return EmailRegistration.of(source.getEmail());
    }

    @Named("getDocument")
    static Document getDocument(FullCustomerDTO source) {
        return Document.of(source.getDocument());
    }

}
