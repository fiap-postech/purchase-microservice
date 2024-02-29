package br.com.fiap.tech.challenge.purchase.application.fixture;

import br.com.fiap.tech.challenge.purchase.application.dto.PaymentDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentDTOFixture {

    public static Model<PaymentDTO> paymentDTOModel() {
        return Instancio.of(PaymentDTO.class)
                .generate(field(PaymentDTO::getId), gen -> gen.text().uuid())
                .generate(field(PaymentDTO::getPaymentId), gen -> gen.text().uuid())
                .generate(field(PaymentDTO::getCreated), gen -> gen.temporal().localDateTime())
                .set(field(PaymentDTO::getPaymentUrl), "https://paga.eu/10234")
                .set(field(PaymentDTO::getStatus), PaymentStatus.PAID)
                .toModel();
    }

}
