package pablo.martins.Controllers;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Entity;

import jakarta.servlet.http.HttpServletRequest;
import pablo.martins.DTO.ShortenUrlRequest;
import pablo.martins.DTO.ShortenUrlResponse;
import pablo.martins.Entities.UrlEntity;
import pablo.martins.Repository.UrlRepository;

@RestController
public class UrlController {

    private final UrlRepository urlRepository;

    

    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }



    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request, HttpServletRequest servletRequest){

        String id;

        do{
            id = RandomStringUtils.randomAlphanumeric(5,10);

        }while(urlRepository.existsById(id));
         
        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectURL = servletRequest.getRequestURL().toString().replace("/shorten-url", id);

        return ResponseEntity.ok(new ShortenUrlResponse(redirectURL));

    }

}
