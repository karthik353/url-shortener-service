package com.url.util.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document(collection = "URL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Url {
    private ObjectId _id;
    @Indexed(unique=true)
    private String key;
    private String originalUrl;
    private String active;
    private Timestamp createTimestamp;
    private String userId;
    private long accessCount;
}
