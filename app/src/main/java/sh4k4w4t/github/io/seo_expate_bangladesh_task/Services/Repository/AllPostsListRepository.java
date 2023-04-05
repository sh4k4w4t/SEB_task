package sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructure;

public interface AllPostsListRepository {
    MutableLiveData<List<PostStructure>> getAllPostsList();
}
