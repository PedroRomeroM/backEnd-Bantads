package bantadsBackEnd.microAuth.dto;

import bantadsBackEnd.microAuth.amqp.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ResponseDto {
    Status status;
}
