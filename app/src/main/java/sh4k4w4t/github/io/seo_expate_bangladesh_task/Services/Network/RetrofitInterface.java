package sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Network;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.Comment;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructure;

public interface RetrofitInterface {

    @GET("posts")
    Call<List<PostStructure>> posts();

    @GET("posts/{id}/")
    Call<PostStructure> postDetails(@Path("id") String id);

    @GET("posts/{id}/comments")
    Call<List<Comment>> allComments(@Path("id") String id);

    @POST("posts/{id}/comments")
    Call<Comment> postComments(@Path("id") String id, @Header("Authorization") String bearerToken, @Body RequestBody requestBody);
}