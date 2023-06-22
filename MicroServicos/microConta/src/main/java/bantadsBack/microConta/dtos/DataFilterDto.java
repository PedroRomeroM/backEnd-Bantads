package bantadsBack.microConta.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class DataFilterDto {
    private Date dataInicio;
    private Date dataFim;
    private String cpf;

    public DataFilterDto (String datai, String dataf, String cpf) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        try {
            this.dataInicio = formato.parse(datai);
            this.dataFim = formato.parse(dataf);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat convert = new SimpleDateFormat("dd-MM-yyyy");

        String dataInicioFormatada = convert.format(this.dataInicio);
        this.dataInicio = convert.parse(dataInicioFormatada);

        String dataFimFormatada = convert.format(this.dataFim);
        this.dataFim = convert.parse(dataFimFormatada);

        this.cpf = cpf;
    }

}
