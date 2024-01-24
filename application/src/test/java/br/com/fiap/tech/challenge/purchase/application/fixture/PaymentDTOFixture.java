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
                .generate(field(PaymentDTO::getDate), gen -> gen.temporal().localDate())
                .generate(field(PaymentDTO::getAmount), gen -> gen.math().bigDecimal().min(BigDecimal.valueOf(15.00)))
                .set(field(PaymentDTO::getMethod), PaymentMethod.PAID_MARKET)
                .set(field(PaymentDTO::getStatus), PaymentStatus.PAID)
                .toModel();
    }

}