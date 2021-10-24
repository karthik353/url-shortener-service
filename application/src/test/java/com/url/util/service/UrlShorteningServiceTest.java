package com.url.util.service;


import com.google.common.collect.Lists;
import com.url.util.exception.KeyNotFoundException;
import com.url.util.model.Url;
import com.url.util.repository.UrlRepository;
import com.url.util.util.TestUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UrlShorteningServiceTest {

    UrlRepository urlRepository;
    UrlShorteningServiceImpl urlShorteningService;

    @Before
    public void init() {
        urlRepository = mock(UrlRepository.class);
        urlShorteningService = new UrlShorteningServiceImpl();
        urlShorteningService.setUrlRepository(urlRepository);
    }

    @Test
    public void testGetUrl() throws KeyNotFoundException {
        // Arrange
        when(urlRepository.getUrlRecord(Mockito.anyString())).thenReturn(TestUtility.getUrl());

        // Act
        Url url = urlShorteningService.getUrl("testKey");

        // Assert
        Assert.assertNotNull("Object should be non null", url);
        Assert.assertEquals("Original URL need to match", "testOriginalURL", url.getOriginalUrl());
        Assert.assertEquals("Access count need to be incremented", 1L, url.getAccessCount());
    }

    @Test(expected = KeyNotFoundException.class)
    public void testGetNullUrl() throws KeyNotFoundException {
        // Arrange
        when(urlRepository.getUrlRecord(Mockito.anyString())).thenReturn(null);

        // Act
        urlShorteningService.getUrl("testKey");
    }

    @Test
    public void testGetAllUrls() {
        // Arrange
        when(urlRepository.findAll()).thenReturn(TestUtility.getAllUrls());

        // Act
        List<Url> returnedList = urlShorteningService.getAllUrls();

        // Assert
        Assert.assertEquals(1, returnedList.size());
    }

    @Test
    public void testGetUrlRecordByOriginalUrl() {
        // Arrange
        when(urlRepository.getUrlRecordByOriginalUrl(Mockito.anyString())).thenReturn(TestUtility.getUrl());

        // Act
        Url url = urlShorteningService.getUrlRecordByOriginalUrl("testOriginalURL");

        // Assert
        Assert.assertNotNull("Object should be non null", url);
        Assert.assertEquals("Original URL need to match", "testOriginalURL", url.getOriginalUrl());
        Assert.assertEquals("Access count need to be incremented", 0L, url.getAccessCount());
    }

    @Test
    public void testCreateKey() throws Exception {
        // Arrange
        Url inputUrl = new Url();
        inputUrl.setOriginalUrl("https://www.google.com/");
        when(urlRepository.insert(Mockito.any(Url.class))).thenReturn(Mockito.any(Url.class));

        // Act
        Url url = urlShorteningService.createKey(inputUrl);

        // Assert
        Assert.assertNotNull(url);
        Assert.assertEquals("https://www.google.com/", url.getOriginalUrl());
        Assert.assertTrue(StringUtils.hasLength(url.getKey()));
    }

}
