package com.url.util.controller;

import com.url.util.exception.KeyNotFoundException;
import com.url.util.model.Url;
import com.url.util.service.UrlShorteningService;
import com.url.util.util.TestUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UrlControllerTest {

    UrlShorteningService urlShorteningService;
    UrlController urlController;

    @Before
    public void init() {
        urlShorteningService = mock(UrlShorteningService.class);
        urlController = new UrlController();
        urlController.setUrlShorteningService(urlShorteningService);
    }

    @Test
    public void testGetUrl() throws KeyNotFoundException {
        // Arrange
        when(urlShorteningService.getUrl(Mockito.anyString())).thenReturn(TestUtility.getUrl());

        // Act
        ResponseEntity<Object> entity = urlController.getUrl("testKey");

        // Assert
        Assert.assertNotNull(entity);
        Assert.assertEquals("testOriginalURL", ((Url) Objects.requireNonNull(entity.getBody())).getOriginalUrl());
    }

    @Test
    public void testGetsUrls() {
        // Arrange
        when(urlShorteningService.getAllUrls()).thenReturn(TestUtility.getAllUrls());

        // Act
        ResponseEntity<Object> entity = urlController.getsUrls();

        // Assert
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getBody());
    }

    @Test
    public void testCreateNewShortenedUrl() throws Exception {
        // Arrange
        when(urlShorteningService.createKey(Mockito.any(Url.class))).thenReturn(TestUtility.getUrl());

        // Act
        ResponseEntity<Object> entity = urlController.createNewShortenedUrl(TestUtility.getUrl());

        // Assert
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getBody());
    }

    @Test
    public void testCreateNewShortenedUrlBadRequest() throws Exception {
        // Arrange
        when(urlShorteningService.createKey(Mockito.any(Url.class))).thenReturn(TestUtility.getUrl());

        // Act
        ResponseEntity<Object> entity = urlController.createNewShortenedUrl(null);

        // Assert
        Assert.assertNotNull(entity);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }

}
