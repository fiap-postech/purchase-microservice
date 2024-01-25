package br.com.fiap.tech.challenge.purchase.launcher.fixture;

import br.com.fiap.tech.challenge.purchase.application.dto.FullCustomerDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDTOFixture {

    public static Model<FullCustomerDTO> customerDTOModel() {
        return Instancio.of(FullCustomerDTO.class)
                .generate(field(FullCustomerDTO::getId), gen -> gen.text().uuid())
                .generate(field(FullCustomerDTO::getEmail), gen -> gen.text().pattern("#a#a#a#a#a#a@domain.com"))
                .set(field(FullCustomerDTO::getDocument), "19748826325")
                .toModel();
    }

}
