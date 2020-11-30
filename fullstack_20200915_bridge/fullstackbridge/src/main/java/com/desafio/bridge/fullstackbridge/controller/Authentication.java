package com.desafio.bridge.fullstackbridge.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import com.desafio.bridge.fullstackbridge.rules.AppAuthentication;
import com.desafio.bridge.fullstackbridge.controller.util.Cookies;
import com.desafio.bridge.fullstackbridge.model.entity.AuthenticationModel;

@RestController
public class Authentication {

    // @RequestMapping(
    // method = RequestMethod.POST,
    // produces = "application/json",
    // value = "/signup"
    // )
    // public String doPost(
    // @RequestBody BridgeUser signupUser, // JSON: {"name":"rafael",
    // "login":"rafael", "pass":"queroserbridge"}
    // HttpServletResponse response
    // ) {
    // // verifica no banco
    // // cadastra no banco
    // // bridgeUserRepository.save(signupUser);
    // // efetua login

    // String retorno="" + signupUser.toString();
    // Boolean validate = false;

    // BridgeUserDAO dao = new BridgeUserDAO();
    // dao.createBridgeUser(signupUser);

    // // BridgeUser validar = dao.readBridgeUser(signupUser.getLogin());

    // // if (validar.getLogin().equalsIgnoreCase(signupUser.getLogin())) {
    // // retorno = "já existe!";
    // // } else {
    // // retorno = "não existe!";
    // // response.addCookie(Cookies.getCookie("authcookie", "true"));
    // // response.addCookie(Cookies.getCookie("bridgeusercookie",
    // signupUser.getLogin()));
    // // }

    // return retorno;
    // }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/signin")
    public Boolean doPost(@RequestBody AuthenticationModel auth, // JSON: {"user":"01010101012", "pass":"rafael"}
            HttpServletResponse response) {
        Boolean validate = false;
        if (AppAuthentication.validate(auth)) { // -> está validando automatico
            response.addCookie(Cookies.getCookie("authcookie", "true"));
            response.addCookie(Cookies.getCookie("bridgeusercookie", auth.getUser()));
            validate = true;
        }
        return validate;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/signout/{bridgeUserName}")
    public Boolean doPost(@CookieValue(value = "authcookie", defaultValue = "false") String authcookie,
            @CookieValue(value = "bridgeusercookie", defaultValue = "null") String bridgeusercookie,
            @PathVariable String bridgeUserName, HttpServletResponse response) {
        Boolean validate = false;
        if (AppAuthentication.isAuthenticated(authcookie)
                && AppAuthentication.isUser(bridgeusercookie, bridgeUserName)) {
            response.addCookie(Cookies.deleteCookie("authcookie", "true"));
            response.addCookie(Cookies.deleteCookie("bridgeusercookie", bridgeUserName));
            validate = true;
        }
        return validate;
    }

}
