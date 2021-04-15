package adyl.task.exception;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class MassageTypeException extends Exception {
    public MassageTypeException(String message) {
        super(message);
    }
}
