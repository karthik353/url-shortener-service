package com.url.util.controller;

import com.url.util.exception.KeyNotFoundException;
import com.url.util.model.Url;
import com.url.util.service.UrlShorteningService;
import com.url.util.util.UrlConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UrlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlController.class);

    @Autowired
    private final UrlShorteningService urlShorteningService;

    public UrlController(UrlShorteningService urlShorteningService) {
        this.urlShorteningService = urlShorteningService;
    }

    @PostMapping("/url/{key}")
    public ResponseEntity<Object> getUrl(@PathVariable("key") String key) throws KeyNotFoundException {
        LOGGER.info("Received a request to fetch the URL details for the key={}" , key);
        return ResponseEntity.ok(urlShorteningService.getUrl(key));
    }

    @GetMapping("/allUrls")
    public ResponseEntity<Object> getsUrls() {
        LOGGER.info("Received a request to fetch the URL details");
        return ResponseEntity.ok(urlShorteningService.getAllUrls());
    }

    @PostMapping("/")
    public ResponseEntity<Object> createNewShortenedUrl(@RequestBody Url url) throws Exception {
        LOGGER.info("Received a request to add a new URL detail. url={}", url);
        if (Objects.isNull(url) || !StringUtils.hasLength(url.getOriginalUrl())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UrlConstants.BAD_REQUEST);
        }
        return ResponseEntity.ok(urlShorteningService.createKey(url));
    }

}
