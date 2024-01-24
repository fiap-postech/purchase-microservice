package br.com.fiap.tech.challenge.purchase.application.util;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

import static java.util.Collections.emptyList;

public record ResponseList<T extends Serializable>(
        Integer number,
        Integer size,
        Integer numberOfElements,
        Long totalElements,
        List<T> content
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -6476964575777514361L;

    public static <T extends Serializable, E extends Serializable> ResponseList<T> from(ResponseList<E> list, Function<E, T> parser){
        return new ResponseList<>(
                list.number(),
                list.size(),
                list.numberOfElements(),
                list.totalElements(),
                list.content().stream().map(parser).toList()
        );
    }

    public static <T extends Serializable> ResponseList<T> empty(){
        return new ResponseList<>(
                0,
                0,
                0,
                0L,
                emptyList()
        );
    }
}
