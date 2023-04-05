package sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructureModel;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Network.RetrofitAPI;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Network.RetrofitInterface;

public class PostDetailsRepositoryImplementation implements PostDetailsRepository{
    private PostStructureModel postDetails;
    private MutableLiveData<PostStructureModel> mutableLiveDataPostDetails;

    private static PostDetailsRepositoryImplementation instance;
    public static PostDetailsRepositoryImplementation getInstance(){
        if (instance==null){
            instance= new PostDetailsRepositoryImplementation();
        }
        return instance;
    }

    @Override
    public MutableLiveData<PostStructureModel> postDetails(String id) {
        if (postDetails==null){
            mutableLiveDataPostDetails= new MutableLiveData<>();
        }

        RetrofitInterface retrofitInterface= RetrofitAPI.getRetrofitInterface();
        Call<PostStructureModel> callData= retrofitInterface.postDetails(id);
        callData.clone().enqueue(new Callback<PostStructureModel>() {
            @Override
            public void onResponse(@NonNull Call<PostStructureModel> call, @NonNull Response<PostStructureModel> response) {
                try {
                    if (response.isSuccessful()){
                        postDetails= response.body();
                        mutableLiveDataPostDetails.postValue(postDetails);
                    }

                }catch (Exception e){
                    mutableLiveDataPostDetails.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostStructureModel> call, @NonNull Throwable t) {
                mutableLiveDataPostDetails.postValue(null);
            }
        });
        return mutableLiveDataPostDetails;
    }
}
