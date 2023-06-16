package bantadsbackend.microGerentes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseDto {
    private long gerenteId;
    private Status status;
}
