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
    POETRY(1L, "POETRY"),
    FUTURISM(2L, "FUTURISM"),
    PROSE(3L, "PROSE"),
    DYSTOPIA(4L, "DYSTOPIA");

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
