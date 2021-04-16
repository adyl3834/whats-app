package adyl.task.exception;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class MassageTypeException extends RuntimeException {
    public MassageTypeException(String message) {
        super(message);
    }
}
