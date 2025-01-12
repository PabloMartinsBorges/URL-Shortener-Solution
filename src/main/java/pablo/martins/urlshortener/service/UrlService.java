package pablo.martins.urlshortener.service;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import pablo.martins.urlshortener.dto.ShortenUrlRequest;
import pablo.martins.urlshortener.entities.UrlEntity;
import pablo.martins.urlshortener.repository.UrlRepository;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String encurtarUrl(ShortenUrlRequest request,
                                          HttpServletRequest servletRequest) {

        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1)));

        return servletRequest.getRequestURL().toString().replace("shorten-url", id);
    }

    public HttpHeaders redirecionaUrl(String id){

        var url = urlRepository.findById(id);

        if (url.isEmpty()) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getFullUrl()));

        return headers;
    }
}
