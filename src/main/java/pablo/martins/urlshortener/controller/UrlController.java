package pablo.martins.urlshortener.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pablo.martins.urlshortener.dto.ShortenUrlRequest;
import pablo.martins.urlshortener.dto.ShortenUrlResponse;
import pablo.martins.urlshortener.service.UrlService;

@RestController
public class UrlController {

    private final UrlService urlService;


    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request,
                                           HttpServletRequest servletRequest) {

        var redirectUrl = urlService.encurtarUrl(request, servletRequest);

        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {

        var resultado = urlService.redirecionaUrl(id);

        if (resultado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).headers(resultado).build();
    }

}
