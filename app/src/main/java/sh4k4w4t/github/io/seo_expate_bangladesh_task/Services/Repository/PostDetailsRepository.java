package sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository;

import androidx.lifecycle.MutableLiveData;

import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructureModel;

public interface PostDetailsRepository {
    MutableLiveData<PostStructureModel> postDetails(String id);
}
