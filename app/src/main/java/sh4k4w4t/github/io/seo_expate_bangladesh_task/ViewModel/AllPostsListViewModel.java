package sh4k4w4t.github.io.seo_expate_bangladesh_task.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructureModel;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository.AllPostsListRepository;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository.AllPostsListRepositoryImplementation;

public class AllPostsListViewModel extends AndroidViewModel {
    AllPostsListRepository repository;

    public AllPostsListViewModel(@NonNull Application application) {
        super(application);
        repository= AllPostsListRepositoryImplementation.getInstance();
    }

    public MutableLiveData<List<PostStructureModel>> allPosts(){
        return repository.getAllPostsList();
    }

}
