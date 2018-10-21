package com.dimitrov.github.githubclient.repository;

import org.eclipse.egit.github.core.Repository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import java.util.List;

public interface GithubRepository {
    @GET("user/repos")
    Call<List<Repository>> listRepositories(@Header("Authorization") String accessToken,
                                            @Header("Accept") String apiVersion);
}
