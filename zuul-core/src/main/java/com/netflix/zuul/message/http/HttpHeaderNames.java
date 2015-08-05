/*
 *
 *
 *  Copyright 2013-2015 Netflix, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 * /
 */

package com.netflix.zuul.message.http;

import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.zuul.message.HeaderName;

/**
 * A cache of both constants for common HTTP header names, and custom added header names.
 *
 * Primarily to be used as a performance optimization for avoiding repeatedly doing lower-casing and
 * case-insensitive comparisons of StringS.
 *
 * User: Mike Smith
 * Date: 8/5/15
 * Time: 12:33 PM
 */
public class HttpHeaderNames
{
    private static final DynamicIntProperty MAX_CACHE_SIZE =
            DynamicPropertyFactory.getInstance().getIntProperty("com.netflix.zuul.message.http.HttpHeaderNames.maxCacheSize", 30);

    private static final HttpHeaderNamesCache HEADER_NAME_CACHE = new HttpHeaderNamesCache(100, MAX_CACHE_SIZE.get());
    
    public static final HeaderName COOKIE = HEADER_NAME_CACHE.get("Cookie");
    public static final HeaderName SET_COOKIE = HEADER_NAME_CACHE.get("Set-Cookie");

    public static final HeaderName DATE = HEADER_NAME_CACHE.get("Date");
    public static final HeaderName CONNECTION = HEADER_NAME_CACHE.get("Connection");
    public static final HeaderName HOST = HEADER_NAME_CACHE.get("Host");
    public static final HeaderName SERVER = HEADER_NAME_CACHE.get("Server");
    public static final HeaderName VIA = HEADER_NAME_CACHE.get("Via");
    public static final HeaderName USER_AGENT = HEADER_NAME_CACHE.get("User-Agent");
    public static final HeaderName REFERER = HEADER_NAME_CACHE.get("Referer");

    public static final HeaderName CONTENT_TYPE = HEADER_NAME_CACHE.get("Content-Type");
    public static final HeaderName CONTENT_LENGTH = HEADER_NAME_CACHE.get("Content-Length");
    public static final HeaderName CONTENT_ENCODING = HEADER_NAME_CACHE.get("Content-Encoding");
    public static final HeaderName ACCEPT = HEADER_NAME_CACHE.get("Accept");
    public static final HeaderName ACCEPT_ENCODING = HEADER_NAME_CACHE.get("Accept-Encoding");
    public static final HeaderName ACCEPT_LANGUAGE = HEADER_NAME_CACHE.get("Accept-Language");

    public static final HeaderName LAST_MODIFIED = HEADER_NAME_CACHE.get("Last-Modified");
    public static final HeaderName ETAG = HEADER_NAME_CACHE.get("ETag");
    public static final HeaderName CACHE_CONTROL = HEADER_NAME_CACHE.get("Cache-Control");
    public static final HeaderName PRAGMA = HEADER_NAME_CACHE.get("Pragma");

    /**
     * Looks up the name in the cache, and if does not exist, then creates and adds a new one
     * (up to the max cache size).
     *
     * @param name
     * @return HeaderName - never null.
     */
    public static HeaderName get(String name)
    {
        return HEADER_NAME_CACHE.get(name);
    }
}
