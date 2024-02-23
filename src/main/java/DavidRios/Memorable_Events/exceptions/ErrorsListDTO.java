package DavidRios.Memorable_Events.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorsListDTO extends ErrorsDTO {
    private final List<String> errorList;

    public ErrorsListDTO (String message, LocalDateTime timeStamp, List<String> errorList) {
        super(message, timeStamp);
        this.errorList = errorList;
    }
}
