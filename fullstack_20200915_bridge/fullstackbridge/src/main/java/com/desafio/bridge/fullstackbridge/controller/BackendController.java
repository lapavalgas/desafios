package com.desafio.bridge.fullstackbridge.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import com.desafio.bridge.fullstackbridge.model.entity.GithubSearchList;
import com.desafio.bridge.fullstackbridge.rules.ApiSearch;
import com.desafio.bridge.fullstackbridge.rules.AppAuthentication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BackendController {

    // search -> retriev json project data list
    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/search/{q}")
    public String doGet(@CookieValue(value = "authcookie", defaultValue = "false") String authcookie,
            @CookieValue(value = "bridgeusercookie", defaultValue = "null") String bridgeusercookie,
            HttpServletResponse response, @PathVariable String q) {
        // if (AppAuthentication.isAuthenticated(authcookie)) {
        // } ok
        GithubSearchList githubSearchList = new ApiSearch().searchOnGithub(q).getBody();
        ObjectMapper mapper = new ObjectMapper();
        String json = q;
        try {
            json = mapper.writeValueAsString(githubSearchList);
            // System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } 
        return json;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/search/{q}/{language}")
    public String doGet(@CookieValue(value = "authcookie", defaultValue = "false") String authcookie,
            @CookieValue(value = "bridgeusercookie", defaultValue = "null") String bridgeusercookie,
            HttpServletResponse response, @PathVariable String q, @PathVariable String language) {
        String jsonObject = "{}";
        // if (AppAuthentication.isAuthenticated(authcookie)) {
        // }
        jsonObject = new ApiSearch().searchOnGithub(q, language).getBody().toString();
        return jsonObject;
    }

}
