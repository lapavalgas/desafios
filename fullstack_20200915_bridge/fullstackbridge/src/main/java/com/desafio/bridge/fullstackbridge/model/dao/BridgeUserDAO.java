package com.desafio.bridge.fullstackbridge.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.desafio.bridge.fullstackbridge.model.entity.BridgeUser;
import com.desafio.bridge.fullstackbridge.model.entity.BridgeUserFavorite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BridgeUserDAO {

    @Autowired
    BridgeUserRepository bridgeUserRepository;

    @Autowired
    BridgeUserFavoriteRepository bridgeUserFavoriteRepository;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/user")
    public void createBridgeUser(@RequestBody BridgeUser bridgeUser, HttpServletResponse response) {
        // JSON: {"name":"rafael", "login":"rafael","pass":"queroserbridge"}
        try {
            if (bridgeUserRepository.findByLogin(bridgeUser.getLogin()) == null) {
                bridgeUserRepository.save(bridgeUser);
            } else {
                response.setStatus(400);
                response.setHeader("error", "Broken data!   :(  ");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        bridgeUserRepository.save(bridgeUser);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/user/id/{id}")
    public BridgeUser readBridgeUser(@PathVariable Integer id, HttpServletResponse response) {
        BridgeUser bridgeUser = null;
        try {
            if (bridgeUserRepository.findById(id) == null) {
                response.setStatus(400);
                response.setHeader("error", "Broken data!   :(  ");
            } else {
                bridgeUser = bridgeUserRepository.findById(id).get();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return bridgeUser;
    }

    @CrossOrigin

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/user/login/{login}")
    public BridgeUser readBridgeUser(@PathVariable String login, HttpServletResponse response) {
        BridgeUser bridgeUser = null;
        try {
            if (bridgeUserRepository.findByLogin(login) == null) {
                response.setStatus(400);
                response.setHeader("error", "Broken data!   :(  ");
            } else {
                bridgeUser = bridgeUserRepository.findByLogin(login);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return bridgeUser;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", value = "/user")
    public void updateBridgeUser(@RequestBody BridgeUser bridgeUser, HttpServletResponse response) {
        // JSON: {"id":"2", "name":"rafael", "login":"rafael","pass":"queroserbridge"}
        try {
            if (bridgeUserRepository.findById(bridgeUser.getId()).get() == null) {
                response.setStatus(400);
                response.setHeader("error", "Broken data!   :(  ");
            } else {
                bridgeUserRepository.save(bridgeUser);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        bridgeUserRepository.save(bridgeUser);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/user/{id}")
    public void deleteBridgeUser(@PathVariable Integer id, HttpServletResponse response) {
        try {
            if (bridgeUserRepository.findById(id) == null) {
                response.setStatus(401);
                response.setHeader("error", "Broken data :(  !");
            } else {
                bridgeUserRepository.deleteById(id);
                // deletar favoritos...
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // @ResponseBody
    // @RequestMapping(method = RequestMethod.DELETE, produces = "application/json",
    // value = "/user")
    // public void deleteBridgeUser(@RequestBody BridgeUser bridgeUser,
    // HttpServletResponse response) {
    // // JSON: {"id":"2", "name":"rafael",
    // "login":"rafael","pass":"queroserbridge"}
    // bridgeUserRepository.delete(bridgeUser);
    // }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/user/favorites")
    public void createBridgeUserFavorite(@RequestBody BridgeUserFavorite bridgeUserFavorite,
            HttpServletResponse response) {
        // JSON : {"bridgeUserId":"26", "favoriteGithubRepositoryUrl":"https://"}
        // no security...
        try {
            List<BridgeUserFavorite> favorites = readAllBridgeUserFavorite(bridgeUserFavorite.getBridgeUserId(),
                    response);
            for (BridgeUserFavorite buf : favorites) {
                if (buf.getFavoriteGithubRepositoryUrl()
                        .equalsIgnoreCase(bridgeUserFavorite.getFavoriteGithubRepositoryUrl())) {
                    response.setStatus(401);
                    response.setHeader("error", "Broken data :(  !");
                } else {
                    bridgeUserFavoriteRepository.save(bridgeUserFavorite);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/user/favorites/{bridgeUserId}")
    public List<BridgeUserFavorite> readAllBridgeUserFavorite(@PathVariable Integer bridgeUserId,
            HttpServletResponse response) {
        List<BridgeUserFavorite> bridgeUserFavorite = bridgeUserFavoriteRepository.findAll();
        List<BridgeUserFavorite> favorites = new ArrayList<>();
        for (BridgeUserFavorite buf : bridgeUserFavorite) {
            if (buf.getBridgeUserId() == bridgeUserId) {
                favorites.add(buf);
            }
        }
        return favorites;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/user/favorites/{id}")
    public void deleteBridgeUserFavorite(@PathVariable Integer id, HttpServletResponse response) {
        try {
            bridgeUserFavoriteRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}
