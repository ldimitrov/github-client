package com.dimitrov.github.githubclient.service;

import com.dimitrov.github.githubclient.repository.GithubRepository;
import org.eclipse.egit.github.core.Repository;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

@Service
public class GithubService {
    static final String API_BASE_URL = "https://api.github.com/";
    static final String API_VERSION_SPEC = "application/vnd.github.v3+json";
    static final String JSON_CONTENT_TYPE = "application/json";

    private String accessToken;
    private GithubRepository repository;

    public GithubService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repository = retrofit.create(GithubRepository.class);
        this.accessToken = "token " + System.getenv("ACCESS_TOKEN");
    }

    public List<Repository> getRepositories() throws IOException {
        Call<List<Repository>> retrofitCall = repository.listRepositories(accessToken, API_VERSION_SPEC);

        Response<List<Repository>> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
            ? response.errorBody().string() : "Something wrong happend");
        }

        return response.body();
    }

}
