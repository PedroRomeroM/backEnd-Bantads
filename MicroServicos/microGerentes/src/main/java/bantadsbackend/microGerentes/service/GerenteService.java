package bantadsbackend.microGerentes.service;

import bantadsbackend.microGerentes.dto.*;
import bantadsbackend.microGerentes.model.Gerente;
import bantadsbackend.microGerentes.model.GerenteEditar;
import bantadsbackend.microGerentes.repository.GerenteEditarRepository;
import bantadsbackend.microGerentes.repository.GerenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private GerenteEditarRepository gerenteEditarRepository;

    @Autowired
    private ModelMapper modelMapperGerente;

    public List<GerenteDto> selectAllGerente(){
        return gerenteRepository
                .findAll()
                .stream()
                .map(e -> modelMapperGerente.map(e, GerenteDto.class))
                .collect(Collectors.toList());
    }

    public EResponseDto deleteGerente (String cpf){
        //Verificar se existe mais de um gerente
        Integer numeroDeGerentes =  gerenteRepository.verificarGerentes();
        Integer idGerente =  gerenteRepository.getGerenteId(cpf);
        if(numeroDeGerentes > 1){
            gerenteRepository.deleteById(idGerente);
            return new EResponseDto(true,idGerente);
        }
        return new EResponseDto(false,idGerente);
    }

    public ResponseDto createGerente(GerenteDto dto){
        Gerente gerente = modelMapperGerente.map(dto, Gerente.class);
        gerenteRepository.save(gerente);
        ResponseDto rDto = new ResponseDto(gerente.getGerenteId(),gerente.getNomeGerente(),gerente.getCpfGerente(), Status.SUCESSO,dto.getEmailGerente(),dto.getSenhaGerente());
        return rDto;
    }
    public Integer updateGerente(String cpfGerente, GerenteDto dto) {

        Integer gerenteId = gerenteRepository.getGerenteId(cpfGerente);
        Gerente gerente = modelMapperGerente.map(dto,Gerente.class);
        gerente.setGerenteId(gerenteId);
        gerenteRepository.save(gerente);

        return gerenteId;
    }

    public GerenteEditarDTO passarInfoEditarGerente(String cpf){

        GerenteEditar gerenteEditar = gerenteEditarRepository.findByCpf(cpf);
        return modelMapperGerente.map(gerenteEditar, GerenteEditarDTO.class);
    }

}
