package bantadsBack.microConta.dtos.sagaCadastrarCliente;

import bantadsBack.microConta.dtos.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseDto {
    private Status status;
}
