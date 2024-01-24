package br.com.fiap.tech.challenge.purchase.launcher.util;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueueUtil {

    public static <T> void sendMessage(SqsTemplate template, String queue, T element) {
        template.send(to -> to.queue(queue).payload(element));
    }

}
