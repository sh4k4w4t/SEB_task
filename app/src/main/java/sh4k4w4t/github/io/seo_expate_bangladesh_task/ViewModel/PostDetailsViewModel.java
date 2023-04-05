package sh4k4w4t.github.io.seo_expate_bangladesh_task.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructureModel;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository.PostDetailsRepository;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository.PostDetailsRepositoryImplementation;

public class PostDetailsViewModel extends AndroidViewModel {
    PostDetailsRepository repository;

    public PostDetailsViewModel(@NonNull Application application) {
        super(application);
        repository= PostDetailsRepositoryImplementation.getInstance();
    }
    public MutableLiveData<PostStructureModel> postDetails(String id){
        return repository.postDetails(id);
    }
}
