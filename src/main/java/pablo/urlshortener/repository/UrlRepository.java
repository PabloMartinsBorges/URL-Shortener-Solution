package pablo.martins.urlshortener.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pablo.martins.urlshortener.entities.UrlEntity;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}
