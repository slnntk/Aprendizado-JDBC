package jdbcDeletarDados.db;

import java.io.Serial;

public class DbIntegrityException extends RuntimeException{
    @Serial
    private static final long serialVerionUID = 1L;
    public DbIntegrityException(String message) {
        super(message);
    }
}
