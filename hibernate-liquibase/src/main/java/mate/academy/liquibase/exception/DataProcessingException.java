package mate.academy.liquibase.exception;

import java.util.function.Supplier;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataProcessingException(String message) {
        super(message);
    }

    public static Supplier<DataProcessingException> findByIdSupplier(Long id, Class<?> clazz) {
        return () -> new DataProcessingException(
                "Can't find an %s by id %s".formatted(clazz.getName().toLowerCase(), id)
        );
    }
}
