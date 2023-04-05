package sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructureModel;

public interface RetrofitInterface {

    @GET("posts")
    Call<List<PostStructureModel>> allPostsList();
}