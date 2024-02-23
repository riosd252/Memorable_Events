package DavidRios.Memorable_Events.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorsDTO {
    private String message;
    private LocalDateTime timeStamp;
}
