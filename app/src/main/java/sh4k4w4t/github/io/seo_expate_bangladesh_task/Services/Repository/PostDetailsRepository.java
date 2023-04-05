package sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;

import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.Comment;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructure;

public interface PostDetailsRepository {
    MutableLiveData<PostStructure> postDetails(String id);
    MutableLiveData<List<Comment>> allComments(String id);

    MutableLiveData<Comment> postComment(String id, String token, String name, String email, String body);
}
