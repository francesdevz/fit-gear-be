package FirGear.com.Repository;

import FirGear.com.Entity.TokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends MongoRepository<TokenEntity, Long> {
    public TokenEntity findByEmailAddress(String emailAddress);
}
