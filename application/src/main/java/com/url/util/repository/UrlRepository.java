package com.url.util.repository;

import com.url.util.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UrlRepository extends MongoRepository<Url, String> {
    @Query("{key:'?0'}")
    Url getUrlRecord(String key);

    @Query("{originalUrl:'?0'}")
    Url getUrlRecordByOriginalUrl(String url);
}
