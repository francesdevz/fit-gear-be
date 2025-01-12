package FirGear.com.Repository;

import FirGear.com.Entity.RegisterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends MongoRepository<RegisterEntity, String> {

    /**
     * Custom query to find a user by their email address.
     *
     * @param emailAddress The email address to search for.
     * @return The RegisterEntity object corresponding to the user or null if not found.
     */
    RegisterEntity findByEmailAddress(String emailAddress);

}
