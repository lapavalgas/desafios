package com.desafio.bridge.fullstackbridge.model.entity;

import java.util.List;

import lombok.Data;

@Data
public class GithubSearchList {
    String total_count;
    String incomplete_results;
    List<GithubProject> items;
}
 