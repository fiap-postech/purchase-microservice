package br.com.fiap.tech.challenge.purchase.launcher.fixture.input;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PaymentInputDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentInputDTOFixture {

    public static Model<PaymentInputDTO> paymentInputDTO() {
        return Instancio.of(PaymentInputDTO.class)
                .set(field(PaymentInputDTO::getMethod), PaymentMethod.PAID_MARKET)
                .set(field(PaymentInputDTO::getStatus), PaymentStatus.PAID)
                .set(field(PaymentInputDTO::getUrlPayment), "http://paga.eu/12345")
                .generate(field(PaymentInputDTO::getId), gen -> gen.text().uuid())
                .generate(field(PaymentInputDTO::getDate), gen -> gen.temporal().localDate())
                .generate(field(PaymentInputDTO::getAmount), gen -> gen.math().bigDecimal().min(BigDecimal.valueOf(20.00)))
                .toModel();
    }

}
