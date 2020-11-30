package com.desafio.bridge.fullstackbridge.model.dao;

import com.desafio.bridge.fullstackbridge.model.entity.BridgeUser;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BridgeUserRepository extends JpaRepository<BridgeUser, Integer> {

    BridgeUser findByLogin(String login);
 
}  