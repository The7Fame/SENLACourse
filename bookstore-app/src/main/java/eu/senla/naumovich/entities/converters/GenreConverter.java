package eu.senla.naumovich.entities.converters;

import eu.senla.naumovich.entities.enums.Genre;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<Genre, Long> {

    @Override
    public Long convertToDatabaseColumn(Genre genre) {
        if (genre == null) {
            return null;
        }
        return genre.getId();
    }

    @Override
    public Genre convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return Genre.lookup(id);
    }
}
