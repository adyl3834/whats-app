package adyl.task.exception;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class MessageTypeException extends RuntimeException {
    public MessageTypeException(String message) {
        super(message);
    }
}
