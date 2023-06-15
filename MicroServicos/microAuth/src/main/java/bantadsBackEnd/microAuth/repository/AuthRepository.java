package bantadsBackEnd.microAuth.repository;

import bantadsBackEnd.microAuth.model.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthRepository extends MongoRepository<Auth, String> {

    Optional<Auth> findByEmail(String email);

    Auth deleteByEmail(String email);


}
