package com.desafio.bridge.fullstackbridge.rules;

import com.desafio.bridge.fullstackbridge.model.entity.GithubOwnerProjects;
import com.desafio.bridge.fullstackbridge.model.entity.GithubProjectOwner;
import com.desafio.bridge.fullstackbridge.model.entity.GithubSearchList;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiSearch {

    static String githubUrl = "https://api.github.com";
    String githubSearchUrl = "/search/repositories?q=";
    String sortOrder = "&sort=stars&order=desc";

    public ResponseEntity<String> getAnythingOnGithub(String url) {
        return new RestTemplate().exchange(url, HttpMethod.GET, null, String.class);
    }

    public ResponseEntity<GithubSearchList> searchOnGithub(String q) {
        final String url = githubUrl + githubSearchUrl + q + sortOrder;
        return new RestTemplate().exchange(url, HttpMethod.GET, null, GithubSearchList.class);
    }

    public ResponseEntity<GithubSearchList> searchOnGithub(String q, String language) {
        final String url = githubUrl + githubSearchUrl + q + "+language:" + language + sortOrder;
        return new RestTemplate().exchange(url, HttpMethod.GET, null, GithubSearchList.class);
    }

	public ResponseEntity<GithubProjectOwner> getProjectOwnerOnGithub(String url) {
		return new RestTemplate().exchange(url, HttpMethod.GET, null, GithubProjectOwner.class);
	}
	public ResponseEntity<GithubProjectOwner> getProjectOwnerOnGithub(GithubProjectOwner githubProjectOwner) {
		return new RestTemplate().exchange(githubProjectOwner.getUrl(), HttpMethod.GET, null, GithubProjectOwner.class);
    }
    
	public ResponseEntity<GithubOwnerProjects> getOwnerReposOnGithub(String url) {
		return new RestTemplate().exchange(url, HttpMethod.GET, null, GithubOwnerProjects.class);
	}
	public ResponseEntity<GithubOwnerProjects> getOwnerReposOnGithub(GithubProjectOwner githubProjectOwner) {
		return new RestTemplate().exchange(githubProjectOwner.getPublic_repos(), HttpMethod.GET, null, GithubOwnerProjects.class);
	}

}
