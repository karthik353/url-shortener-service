package com.url.util.exception;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UrlExceptionHandlerTest {

    UrlExceptionHandler urlExceptionHandler;

    @Before
    public void init() {
        urlExceptionHandler = new UrlExceptionHandler();
    }

    @Test
    public void testHandleHashException() {
        // Act
        ResponseEntity<Object> entity = urlExceptionHandler.handleHashException(new HashException("testException","testExceptionDescription"));

        // Assert
        Assert.assertNotNull(entity);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, entity.getStatusCode());
        Assert.assertNotNull(entity.getBody());
    }

    @Test
    public void testHandleUriException() {
        // Act
        ResponseEntity<Object> entity = urlExceptionHandler.handleUriException(new KeyNotFoundException("testException","testExceptionDescription"));

        // Assert
        Assert.assertNotNull(entity);
        Assert.assertEquals(HttpStatus.BAD_GATEWAY, entity.getStatusCode());
        Assert.assertNotNull(entity.getBody());
    }
}
