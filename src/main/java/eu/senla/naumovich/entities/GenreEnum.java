package eu.senla.naumovich.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum GenreEnum {
    GENRE1(1L, "genre1"), GENRE2(2L,"genre2");
    private final Long id;
    private final String genreName;

    private final static Map<Long, GenreEnum> LOOKUP;

    static {
        LOOKUP = Collections.unmodifiableMap(Arrays.stream(GenreEnum.values())
                .collect(Collectors.toMap(GenreEnum::getId, Function.identity())));
    }
    public static GenreEnum lookup(long genreId) {
        return LOOKUP.get(genreId);
    }
}
