package br.com.fiap.tech.challenge.purchase.launcher.fixture;

import br.com.fiap.tech.challenge.purchase.application.dto.CustomerDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDTOFixture {

    public static Model<CustomerDTO> customerDTOModel() {
        return Instancio.of(CustomerDTO.class)
                .generate(field(CustomerDTO::getId), gen -> gen.text().uuid())
                .generate(field(CustomerDTO::getEmail), gen -> gen.text().pattern("#a#a#a#a#a#a@domain.com"))
                .set(field(CustomerDTO::getDocument), "19748826325")
                .toModel();
    }

}
