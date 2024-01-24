package br.com.fiap.tech.challenge.purchase.launcher.fixture;

import br.com.fiap.tech.challenge.purchase.application.dto.ProductDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDTOFixture {

    public static Model<ProductDTO> beverageDTOModel() {
        return Instancio.of(ProductDTO.class)
                .generate(field(ProductDTO::getId), gen -> gen.text().uuid())
                .set(field(ProductDTO::getName), "Bebida")
                .set(field(ProductDTO::getPrice), BigDecimal.valueOf(5.00))
                .toModel();
    }

    public static Model<ProductDTO> sideDishDTOModel() {
        return Instancio.of(ProductDTO.class)
                .generate(field(ProductDTO::getId), gen -> gen.text().uuid())
                .set(field(ProductDTO::getName), "Acompanhamento")
                .set(field(ProductDTO::getPrice), BigDecimal.valueOf(3.00))
                .toModel();
    }

    public static Model<ProductDTO> sandwichDTOModel() {
        return Instancio.of(ProductDTO.class)
                .generate(field(ProductDTO::getId), gen -> gen.text().uuid())
                .set(field(ProductDTO::getName), "Lanche")
                .set(field(ProductDTO::getPrice), BigDecimal.valueOf(17.00))
                .toModel();
    }
}
