package com.example.facebookv2.data;

import com.example.facebookv2.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsClient {
    private static final String Base_URL = "https://jsonplaceholder.typicode.com/";
    private PostInterface postInterface;
    private static PostsClient INSTANCE;

    public PostsClient() {
        Retrofit retrofit= new Retrofit.Builder().baseUrl(Base_URL)
           .addConverterFactory(GsonConverterFactory
                   .create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()     ;
        postInterface = retrofit.create(PostInterface.class);
    }

    public static PostsClient getINSTANCE() {
        if(null== INSTANCE){
            INSTANCE=new PostsClient();
        }
        return INSTANCE;
    }

    public Single<List<PostModel>> getPosts(){
        return postInterface.getPosts();
    }
}
