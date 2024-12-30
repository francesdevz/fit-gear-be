package FirGear.com.Repository;

import FirGear.com.Entity.RegisterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends MongoRepository<RegisterEntity, Long> {

}
