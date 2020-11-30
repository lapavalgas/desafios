package com.desafio.bridge.fullstackbridge.controller.util;

import javax.servlet.http.Cookie;

public class Cookies {

    public static Cookie getCookie(String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(1 * 60 * 60);
        cookie.setPath("/");
        return cookie;
    }

    public static Cookie deleteCookie(String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return cookie;
    }

}
