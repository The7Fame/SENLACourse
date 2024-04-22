package eu.senla.naumovich.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Genre {
    GENRE1(1L, "genre1"), GENRE2(2L, "genre2");

    private final Long id;
    private final String genreName;

    private final static Map<Long, Genre> LOOKUP;

    static {
        LOOKUP = Collections.unmodifiableMap(Arrays.stream(Genre.values())
                .collect(Collectors.toMap(Genre::getId, Function.identity())));
    }

    public static Genre lookup(long genreId) {
        return LOOKUP.get(genreId);
    }
}
