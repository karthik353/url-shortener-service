package com.url.util.service;

import com.google.common.hash.Hashing;
import com.url.util.exception.HashException;
import com.url.util.exception.KeyNotFoundException;
import com.url.util.model.Url;
import com.url.util.repository.UrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@Service
public class UrlShorteningServiceImpl implements UrlShorteningService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShorteningServiceImpl.class);

    @Autowired
    private UrlRepository urlRepository;

    public void setUrlRepository(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Url getUrl(String key) throws KeyNotFoundException {
        LOGGER.info("Fetching the URL details for the key={}", key);
        Url url = urlRepository.getUrlRecord(key);
        if (Objects.nonNull(url)) {
            url.setAccessCount(url.getAccessCount() + 1);
            LOGGER.info("Incrementing the accessCount={} for the key={}, originalUrl={}", url.getAccessCount(), key, url.getOriginalUrl());
            urlRepository.save(url);
        } else {
            LOGGER.error("No URL record found with key={}", key);
            throw new KeyNotFoundException("keyNotFound", String.format("No URL record found with key=%s", key));
        }
        LOGGER.info("urlResponse={}", url);
        return url;
    }

    @Override
    public List<Url> getAllUrls() {
        LOGGER.info("Retrieving all the URL records");
        List<Url> urls = urlRepository.findAll();
        LOGGER.info("recordCount={}", urls.size());
        return urls;
    }

    @Override
    public Url createKey(Url url) throws Exception {
        try {
            LOGGER.info("Generating the hash for originalUrl={}", url.getOriginalUrl());
            url.setKey(hashString(url.getOriginalUrl()));
            LOGGER.info("Generated the key={} for the originalUrl={}", url.getKey(), url.getOriginalUrl());
            urlRepository.insert(url);
            LOGGER.info("Successfully stored the URL={}", url);
        } catch (Exception ex) {
            LOGGER.error("Error in creating a key for the inputUrl={}", url.getOriginalUrl(), ex);
            throw new Exception(ex);
        }
        return url;
    }

    private String hashString(String originalUrl) {
        return Hashing.sha256()
                .hashString(originalUrl, StandardCharsets.UTF_8)
                .toString().substring(0, 10);
    }

    @Override
    public Url getUrlRecordByOriginalUrl(String originalUrl) {
        return urlRepository.getUrlRecordByOriginalUrl(originalUrl);
    }
}
