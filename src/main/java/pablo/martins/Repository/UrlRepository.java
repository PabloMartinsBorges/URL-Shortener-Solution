package pablo.martins.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import pablo.martins.Entities.UrlEntity;

public interface UrlRepository extends MongoRepository<UrlEntity, String>{

}
