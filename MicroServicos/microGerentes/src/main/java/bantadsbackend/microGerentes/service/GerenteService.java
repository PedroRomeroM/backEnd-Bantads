package bantadsbackend.microGerentes.service;

import bantadsbackend.microGerentes.dto.GerenteDto;
import bantadsbackend.microGerentes.dto.GerenteEditarDTO;
import bantadsbackend.microGerentes.dto.ResponseDto;
import bantadsbackend.microGerentes.dto.Status;
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

    public void deleteGerente (int gerenteId){ gerenteRepository.deleteById(gerenteId); }

    public ResponseDto createGerente(GerenteDto dto){
        Gerente gerente = modelMapperGerente.map(dto, Gerente.class);
        gerenteRepository.save(gerente);
        ResponseDto rDto = new ResponseDto(gerente.getGerenteId(),gerente.getNomeGerente(),gerente.getCpfGerente(), Status.SUCESSO);
        return rDto;
    }

    public GerenteDto updateGerente(String cpfGerente, GerenteDto dto) {

        Gerente gerente = modelMapperGerente.map(dto, Gerente.class);
        gerente.setCpfGerente(cpfGerente);
        gerente = gerenteRepository.save(gerente);

        return modelMapperGerente.map(gerente, GerenteDto.class);
    }

    public GerenteEditarDTO passarInfoEditarGerente(String cpf){

        GerenteEditar gerenteEditar = gerenteEditarRepository.findByCpf(cpf);
        return modelMapperGerente.map(gerenteEditar, GerenteEditarDTO.class);
    }

}
