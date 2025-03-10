package eu.senla.naumovich.exceptions;

public class NoUserException extends RuntimeException {
    private static final String NO_USER = "Пользователь с идентификатором Id = %d не найден";

    public NoUserException(Long userId) {

        super(String.format(NO_USER, userId));
    }
}
