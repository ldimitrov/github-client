package com.dimitrov.github.githubclient.controller;

import com.dimitrov.github.githubclient.service.GithubService;
import org.eclipse.egit.github.core.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GithubClientController {

    @Autowired
    private GithubService gitHubService;

    @GetMapping("/repos")
    public List<Repository> getRepositories() throws IOException {
        return gitHubService.getRepositories();
    }
}
