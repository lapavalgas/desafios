package com.desafio.bridge.fullstackbridge.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class BridgeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    String login;
    String pass;

    @OneToMany
    List<BridgeUserFavorite> favorites;

    public BridgeUser(){
        this.favorites = new ArrayList<>();
    }

    // public void addUserRepositories(String githubProject){
    //     BridgeUserFavorite newBridgeUserFavorite = new BridgeUserFavorite();
    //     newBridgeUserFavorite.setBridgeUserId(this.id);
    //     newBridgeUserFavorite.setFavoriteGithubRepositoryUrl(githubProject);
    //     favorites.add(newBridgeUserFavorite);
    //     new BridgeUserDAO().createBridgeUserFavorite(newBridgeUserFavorite);
    // }

    // public void removeUserRepositories(BridgeUserFavorite bridgeUserFavorite){
    //     new BridgeUserDAO().deleteBridgeUserFavorite(bridgeUserFavorite);
    // }
    
}
