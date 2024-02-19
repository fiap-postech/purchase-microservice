package br.com.fiap.tech.challenge.purchase.application.fixture;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Payment;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentFixture {

    public static Model<Payment> paymentModel() {
        return Instancio.of(Payment.class)
                .generate(field(Payment::id), gen -> gen.text().uuid())
                .set(field(Payment::status), PaymentStatus.PAID)
                .toModel();
    }

}
