package pablo.martins.urlshortener.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "urls")
public class UrlEntity {

    @Id
    private String id;

    private String fullUrl;

    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime expiresAfter;

    public UrlEntity() {
    }

    public UrlEntity(String id, String fullUrl, LocalDateTime expiresAfter) {
        this.id = id;
        this.fullUrl = fullUrl;
        this.expiresAfter = expiresAfter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public LocalDateTime getExpiresAfter() {
        return expiresAfter;
    }

    public void setExpiresAfter(LocalDateTime expiresAfter) {
        this.expiresAfter = expiresAfter;
    }
}
