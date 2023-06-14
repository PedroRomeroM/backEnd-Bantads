package br.com.bantads.orquestrador.sagas.autocadastro;

import br.com.bantads.orquestrador.dtos.ClienteDto;

public class Validacoes {
    //Validar o cpf do cliente
    public static boolean validarCPF(String cpf) {
        //Remover caracteres especiais.
        cpf = cpf.replaceAll("\\D+", "");

        //Verificar se o CPF possui 11 dígitos.
        if (cpf.length() != 11) {
            return false;
        }

        //Verificar se todos os numeros são iguais.
        if (cpf.equals("00000000000") ||
            cpf.equals("11111111111") ||
            cpf.equals("22222222222") ||
            cpf.equals("33333333333") ||
            cpf.equals("44444444444") ||
            cpf.equals("55555555555") ||
            cpf.equals("66666666666") ||
            cpf.equals("77777777777") ||
            cpf.equals("88888888888") ||
            cpf.equals("99999999999") ||
            cpf.length() != 11){
            return(false);
        }

        //Verificar dígito verificador.
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }

        int primeiroDigitoVerificador = (soma * 10) % 11;
        if (primeiroDigitoVerificador == 10) {
            primeiroDigitoVerificador = 0;
        }

        if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigitoVerificador) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }

        int segundoDigitoVerificador = (soma * 10) % 11;
        if (segundoDigitoVerificador == 10) {
            segundoDigitoVerificador = 0;
        }

        return Character.getNumericValue(cpf.charAt(10)) == segundoDigitoVerificador;
    }
    //Validar se algum campo foi passado nullo
    public static boolean validarCampos(ClienteDto dto) {
        if (dto.getNomeCliente() == null || dto.getEmailCliente() == null || dto.getTelefoneCliente() == null ||
                dto.getCpfCliente() == null || dto.getCpfCliente() == null || dto.getTipoEnderecoCliente() == null ||
                dto.getLogradouroCliente() == null || dto.getNumeroResidenciaCliente() == null ||
                dto.getComplementoResidenciaCliente() == null || dto.getCepCliente() == null ||
                dto.getCidadeCliente() == null || dto.getEstadoCliente() == null) {

            return false;
        }
        return true;
    }
}





