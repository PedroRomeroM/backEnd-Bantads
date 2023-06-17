package bantadsBackEnd.microAuth.controller;

import bantadsBackEnd.microAuth.dto.AuthDto;
import bantadsBackEnd.microAuth.enums.Role;
import bantadsBackEnd.microAuth.model.Auth;
import bantadsBackEnd.microAuth.repository.AuthRepository;
import bantadsBackEnd.microAuth.utils.GerarSenha;
import bantadsBackEnd.microAuth.utils.JsonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthRepository repo;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/auth/login")
    ResponseEntity<Object> auth(@RequestBody AuthDto auth) throws NoSuchAlgorithmException {
        String email = auth.getEmail();
        String password = auth.getSenha();

        if (email == null || password == null) {
            return new ResponseEntity<>("Faltando email ou senha", HttpStatus.BAD_REQUEST);
        }

        Auth authEntity = repo.findByEmail(email);
        String salt = GerarSenha.extractSalt();

        if (authEntity == null) {
            return new ResponseEntity<>(new JsonResponse(false, "Email não registrado", null), HttpStatus.NOT_FOUND);
        }

        Boolean isPasswordCorrect = GerarSenha.verifyUserPassword(authEntity.getSenha(), password, salt);

        if (auth.getEmail().equals(authEntity.getEmail())
                && isPasswordCorrect) {
            try {
                authEntity.setSenha("");
                return new ResponseEntity<>(new JsonResponse(true, "", mapper.map(authEntity, AuthDto.class)), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new JsonResponse(false, "Internal server error while creating JWT", null),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(new JsonResponse(false, "Usuário e/ou senha incorretos", null),
                HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/auth")
    public String register(@RequestBody AuthDto auth) throws NoSuchAlgorithmException {
        if (auth.getEmail() != null && auth.getSenha() != null) {
            Auth authEntity = repo.findByEmail(auth.getEmail());

            if (authEntity != null) {
                return "E-MAIL JÁ USADO";
            }

            if (auth.getRole() == null) {
                auth.setRole(String.valueOf(Role.CLIENTE));
            }

            String password = auth.getSenha();
            String saltValue = GerarSenha.extractSalt();
            String encryptedPassword = GerarSenha.encrypt();

            auth.setSenha(encryptedPassword);
            repo.save(mapper.map(auth, Auth.class));

            return "SUCESSO";
        }

        return "FALHOU";
    }

    @DeleteMapping("/auth/{id}")
    ResponseEntity<Object> delete(String email) {
        if (email != null) {
            try {
                repo.deleteByEmail(email);
            } catch (Exception e) {
                return new ResponseEntity<>(new JsonResponse(false, "Erro ao deletar autenticação do usuário", null), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(new JsonResponse(true, "Autenticação do usuário deletada com sucesso", null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new JsonResponse(false, "Id não fornecido para deletar autenticação", null),
                HttpStatus.BAD_REQUEST);
    }
}
