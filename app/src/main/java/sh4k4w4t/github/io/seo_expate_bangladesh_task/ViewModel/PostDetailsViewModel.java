package sh4k4w4t.github.io.seo_expate_bangladesh_task.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;

import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.Comment;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructure;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository.PostDetailsRepository;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository.PostDetailsRepositoryImplementation;

public class PostDetailsViewModel extends AndroidViewModel {
    PostDetailsRepository repository;

    public PostDetailsViewModel(@NonNull Application application) {
        super(application);
        repository= PostDetailsRepositoryImplementation.getInstance();
    }
    public MutableLiveData<PostStructure> postDetails(String id){
        return repository.postDetails(id);
    }

    public MutableLiveData<List<Comment>> allComments(String id){
        return repository.allComments(id);
    }

}
