package com.desafio.bridge.fullstackbridge.rules;

import com.desafio.bridge.fullstackbridge.model.entity.AuthenticationModel;

public class AppAuthentication {

    // receber um objeto authentication e verificar na base de dados -> got true

    public static boolean validate(AuthenticationModel auth ){
        // verificar na base de dados e libera instalação dos cookies de authenticação
        return true;
    }

    public static boolean isAuthenticated(String auth){
        return auth.contains("true") ? true : false;
    }

    public static boolean isUser(String bridgeusercookie, String bridgeUserName) { 
        return bridgeusercookie.equalsIgnoreCase(bridgeUserName) ? true : false;
    }
}
