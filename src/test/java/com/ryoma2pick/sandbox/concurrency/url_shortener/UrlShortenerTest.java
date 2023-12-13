package com.ryoma2pick.sandbox.concurrency.url_shortener;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlShortenerTest {

    @Test
    void issueIdAndAddToMap() {
        // setup
        UrlShortener urlShortener = new UrlShortener();
        for (int i = 0; i < 1000; i++) {
            urlShortener.issueUniqueId("https://" + i);
        }

        // execute & verify
        String originalUrl1 = "https://bar";
        urlShortener.issueUniqueId(originalUrl1);
        assertEquals(1000, urlShortener.id(originalUrl1));

        // execute & verify
        String originalUrl2 = "https://for";
        urlShortener.issueUniqueId(originalUrl2);
        assertEquals(1001, urlShortener.id(originalUrl2));

        // execute & verify
        String shortenedUrl1 = urlShortener.shortenedUrl(originalUrl1);
        assertEquals("https://short/" + "fE", shortenedUrl1);

        // execute & verify
        String shortenedUrl2 = urlShortener.shortenedUrl(originalUrl2);
        assertEquals("https://short/" + "fF", shortenedUrl2);
    }

    @Test
    void guaranteeExclusiveIncrement() throws InterruptedException {
        // setup
        UrlShortener urlShortener = new UrlShortener();
        // execute
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                urlShortener.issueUniqueId("https://" + i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                urlShortener.issueUniqueId("https://" + (1000 + i));
            }
        });
        // verify
        t1.start();
        t2.start();
        t1.join();
        t1.join();
        int id = urlShortener.issueUniqueId("https://" + "foo");
        assertEquals(2001, id);
    }

    @Test
    void shouldReturnStringsEncodedByBase64() {
        // setup
        UrlShortener urlShortener = new UrlShortener();
        // execute
        // verify
        assertEquals("0", urlShortener.baseConvert(0));
        assertEquals("/", urlShortener.baseConvert(63));
        assertEquals("10", urlShortener.baseConvert(64));
        assertEquals("1/", urlShortener.baseConvert(127));
        assertEquals("20", urlShortener.baseConvert(128));
        assertEquals("k0", urlShortener.baseConvert(1280));

    }

}