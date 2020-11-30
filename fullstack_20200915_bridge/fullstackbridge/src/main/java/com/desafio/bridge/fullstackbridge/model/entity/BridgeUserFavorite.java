package com.desafio.bridge.fullstackbridge.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class BridgeUserFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    Integer bridgeUserId;
    String favoriteGithubRepositoryUrl;

}
 

