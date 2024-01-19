package br.com.fiap.tech.challenge.purchase.adapter.fixture;

import br.com.fiap.tech.challenge.purchase.adapter.dto.CustomerInputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerInputDTOFixture {
    public static Model<CustomerInputDTO> customerInputDTO() {
        return Instancio.of(CustomerInputDTO.class)
                .generate(field(CustomerInputDTO::getEmail), gen -> gen.text().pattern("#a#a#a#a#a#a@domain.com"))
                .set(field(CustomerInputDTO::getDocument), "19748826325")
                .toModel();
    }
}
