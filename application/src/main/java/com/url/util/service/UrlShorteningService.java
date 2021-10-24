package com.url.util.service;

import com.url.util.exception.KeyNotFoundException;
import com.url.util.model.Url;

import java.util.List;

public interface UrlShorteningService {
    Url getUrl(String key) throws KeyNotFoundException;

    List<Url> getAllUrls();

    Url createKey(Url url) throws Exception;

    Url getUrlRecordByOriginalUrl(String url);
}
