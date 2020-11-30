package com.desafio.bridge.fullstackbridge.rules;

import com.desafio.bridge.fullstackbridge.model.entity.GithubOwnerProjects;
import com.desafio.bridge.fullstackbridge.model.entity.GithubProject;
import com.desafio.bridge.fullstackbridge.model.entity.GithubProjectOwner;
import com.desafio.bridge.fullstackbridge.model.entity.GithubSearchList;

public class OrderData {

    public static String orderDataSearchList(String q) {

        GithubSearchList gh = new ApiSearch().searchOnGithub(q).getBody();

        String finish = "";

        int i = 0;
        for (GithubProject projects : gh.getItems()) {
            finish += "\n" + "------------------------------------------------------------------------------";
            finish += "\n" + "-----SEARCH LIST--------------------------------------------------------------";
            finish += "\n" + "------------------------------------------------------------------------------";
            finish += "\n" + "-----project"+i+"------------------------------------------------------------------";
            finish += "\n name: " + projects.getName();
            finish += "\n description: " + projects.getDescription();

            GithubProjectOwner ghOwner = projects.getOwner();
            ghOwner = new ApiSearch().getProjectOwnerOnGithub(ghOwner).getBody();

            finish += "\n owner: " + ghOwner.getName();
            finish += "\n stars: " + projects.getStargazers_count();
            finish += "\n " + "---------------------------------------";

            finish += "\n " + "mais detalhes...";
            finish += "\n issues: " + projects.getOpen_issues_count();
            finish += "\n language: " + projects.getLanguage();
            finish += "\n data: " + projects.getCreated_at();
            finish += "\n " + "-----------------------------------------------------------------------------";
            finish += "\n " + "[lista de repos do user]";
            finish += "\n " + "-----------------------------------------------------------------------------";
            i++;
        }
        return finish;
    }

    public static String orderDataProjectDetail(String q) {

        GithubSearchList gh = new ApiSearch().searchOnGithub(q).getBody();

        String finish = "";
        
        GithubProject projects = gh.getItems().get(0);

        GithubProjectOwner ghOwner = projects.getOwner();

        ghOwner = new ApiSearch().getProjectOwnerOnGithub(ghOwner).getBody();

        finish += "\n" + "------------------------------------------------------------------------------";
        finish += "\n" + "-----PROJECT DETAIL-----------------------------------------------------------";
        finish += "\n --- owner: " + ghOwner.getName() + " ----";
        finish += "\n" + "------------------------------------------------------------------------------";
        finish += "\n name: " + projects.getName();
        finish += "\n description: " + projects.getDescription();
        finish += "\n stars: " + projects.getStargazers_count();
        finish += "\n " + "mais detalhes... QUE DETALHES";
        finish += "\n issues: " + projects.getOpen_issues_count();
        finish += "\n language: " + projects.getLanguage();
        finish += "\n data: " + projects.getCreated_at();

        String userRepoList = ghOwner.getRepos_url();
        GithubOwnerProjects ownerRepos = new ApiSearch().getOwnerReposOnGithub(userRepoList).getBody();

        for (GithubProject ownerProjects : ownerRepos.getItems()) {
            finish += "\n" + "------------------------------------------------------------------------------";
            finish += "\n" + "-----PROJECT DETAIL-----------------------------------------------------------";
            finish += "\n name: " + ownerProjects.getName();
            finish += "\n description: " + ownerProjects.getDescription();
            finish += "\n stars: " + ownerProjects.getStargazers_count();
            finish += "\n " + "---------------------------------------";
            finish += "\n " + "mais detalhes...";
            finish += "\n issues: " + ownerProjects.getOpen_issues_count();
            finish += "\n language: " + ownerProjects.getLanguage();
            finish += "\n data: " + ownerProjects.getCreated_at();
            finish += "\n" + "------------------------------------------------------------------------------";
        }
        finish += "\n" + "------------------------------------------------------------------------------";
        return finish;
    }

}
