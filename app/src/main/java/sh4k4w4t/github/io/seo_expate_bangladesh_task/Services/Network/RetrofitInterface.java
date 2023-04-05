package sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.CommentModel;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructureModel;

public interface RetrofitInterface {

    @GET("posts")
    Call<List<PostStructureModel>> posts();

    @GET("posts/{id}/")
    Call<PostStructureModel> postDetails(@Path("id") String id);

    @GET("posts/{id}/comments")
    Call<List<CommentModel>> allComments(@Path("id") String id);
}