package br.com.fiap.tech.challenge.purchase.launcher.fixture;

import br.com.fiap.tech.challenge.purchase.application.dto.FullProductDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDTOFixture {

    public static Model<FullProductDTO> beverageDTOModel() {
        return Instancio.of(FullProductDTO.class)
                .generate(field(FullProductDTO::getId), gen -> gen.text().uuid())
                .set(field(FullProductDTO::getName), "Bebida")
                .set(field(FullProductDTO::getPrice), BigDecimal.valueOf(5.00))
                .toModel();
    }

    public static Model<FullProductDTO> sideDishDTOModel() {
        return Instancio.of(FullProductDTO.class)
                .generate(field(FullProductDTO::getId), gen -> gen.text().uuid())
                .set(field(FullProductDTO::getName), "Acompanhamento")
                .set(field(FullProductDTO::getPrice), BigDecimal.valueOf(3.00))
                .toModel();
    }

    public static Model<FullProductDTO> sandwichDTOModel() {
        return Instancio.of(FullProductDTO.class)
                .generate(field(FullProductDTO::getId), gen -> gen.text().uuid())
                .set(field(FullProductDTO::getName), "Lanche")
                .set(field(FullProductDTO::getPrice), BigDecimal.valueOf(17.00))
                .toModel();
    }
}
