package sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructure;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Network.RetrofitAPI;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Network.RetrofitInterface;

public class AllPostsListRepositoryImplementation implements AllPostsListRepository{

    private List<PostStructure> allPostsList;
    private MutableLiveData<List<PostStructure>> allMutablePostsList;

    private static AllPostsListRepositoryImplementation instance;
    public static AllPostsListRepositoryImplementation getInstance(){
        if (instance==null){
            instance= new AllPostsListRepositoryImplementation();
        }
        return instance;
    }

    @Override
    public MutableLiveData<List<PostStructure>> getAllPostsList() {

        if (allMutablePostsList==null){
            allMutablePostsList= new MutableLiveData<>();
        }

        RetrofitInterface retrofitInterface= RetrofitAPI.getRetrofitInterface();
        Call<List<PostStructure>> callData= retrofitInterface.posts();
        callData.clone().enqueue(new Callback<List<PostStructure>>() {
            @Override
            public void onResponse(@NonNull Call<List<PostStructure>> call, @NonNull Response<List<PostStructure>> response) {
                try {
                    if (response.isSuccessful()){
                        allPostsList= new ArrayList<>();
                        allPostsList= response.body();
                        allMutablePostsList.postValue(allPostsList);
                    }
                }catch (Exception e){
                    allMutablePostsList.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PostStructure>> call, @NonNull Throwable t) {
                allMutablePostsList.postValue(null);
            }
        });

        return allMutablePostsList;
    }
}
