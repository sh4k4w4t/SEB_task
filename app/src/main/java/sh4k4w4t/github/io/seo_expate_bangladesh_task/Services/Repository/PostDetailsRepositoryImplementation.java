package sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.Comment;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructure;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Network.RetrofitAPI;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Network.RetrofitInterface;

public class PostDetailsRepositoryImplementation implements PostDetailsRepository {
    private PostStructure postDetails;
    private MutableLiveData<PostStructure> mutablePostDetails;

    private List<Comment> allComments;
    private MutableLiveData<List<Comment>> mutableAllComments;

    private static PostDetailsRepositoryImplementation instance;

    public static PostDetailsRepositoryImplementation getInstance() {
        if (instance == null) {
            instance = new PostDetailsRepositoryImplementation();
        }
        return instance;
    }

    //Post Details
    @Override
    public MutableLiveData<PostStructure> postDetails(String id) {
        if (postDetails == null) {
            mutablePostDetails = new MutableLiveData<>();
        }

        RetrofitInterface retrofitInterface = RetrofitAPI.getRetrofitInterface();
        Call<PostStructure> callData = retrofitInterface.postDetails(id);
        callData.clone().enqueue(new Callback<PostStructure>() {
            @Override
            public void onResponse(@NonNull Call<PostStructure> call, @NonNull Response<PostStructure> response) {
                try {
                    if (response.isSuccessful()) {
                        postDetails = response.body();
                        mutablePostDetails.postValue(postDetails);
                    }
                } catch (Exception e) {
                    mutablePostDetails.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostStructure> call, @NonNull Throwable t) {
                mutablePostDetails.postValue(null);
            }
        });
        return mutablePostDetails;
    }


    //All comments
    @Override
    public MutableLiveData<List<Comment>> allComments(String id) {
        if (allComments == null) {
            mutableAllComments = new MutableLiveData<>();
        }

        RetrofitInterface retrofitInterface = RetrofitAPI.getRetrofitInterface();
        Call<List<Comment>> listOfComment = retrofitInterface.allComments(id);
        listOfComment.clone().enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(@NonNull Call<List<Comment>> call, @NonNull Response<List<Comment>> response) {
                try {
                    if (response.isSuccessful()) {
                        allComments = response.body();
                        mutableAllComments.postValue(allComments);
                    }
                } catch (Exception e) {
                    mutableAllComments.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Comment>> call, @NonNull Throwable t) {
                mutableAllComments.postValue(null);
            }
        });
        return mutableAllComments;
    }

    public MutableLiveData<Comment> postComment(String id, String token, String name, String email, String body) {
        MutableLiveData<Comment> mutablePostComment = new MutableLiveData<>();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("email", email)
                .addFormDataPart("body", body)
                .build();

        Request request = new Request.Builder()
                .url("https://gorest.co.in/public/v2/posts/"+id+"/comments")
                .addHeader("Authorization", "Bearer " + token)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                e.printStackTrace();
                mutablePostComment.postValue(null);
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    // Parse the responseBody string to create a Comment object
                    // You may need to adjust this based on your Comment class and JSON response
                    Comment comment = new Gson().fromJson(responseBody, Comment.class);
                    mutablePostComment.postValue(comment);
                } else {
                    mutablePostComment.postValue(null);
                }
            }
        });

        return mutablePostComment;
    }
}

