package com.url.util.util;

import com.google.common.collect.Lists;
import com.url.util.model.Url;

import java.util.List;

public class TestUtility {

    public static Url getUrl() {
        Url url = new Url();
        url.setKey("testKey");
        url.setOriginalUrl("testOriginalURL");
        url.setAccessCount(0L);
        return url;
    }

    public static List<Url> getAllUrls() {
        List<Url> urlList = Lists.newArrayList();
        urlList.add(getUrl());
        return urlList;
    }

}
