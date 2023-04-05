package sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.CommentModel;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructureModel;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Network.RetrofitAPI;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Network.RetrofitInterface;

public class PostDetailsRepositoryImplementation implements PostDetailsRepository{
    private PostStructureModel postDetails;
    private MutableLiveData<PostStructureModel> mutablePostDetails;

    private List<CommentModel> allComments;
    private MutableLiveData<List<CommentModel>> mutableAllComments;

    private static PostDetailsRepositoryImplementation instance;
    public static PostDetailsRepositoryImplementation getInstance(){
        if (instance==null){
            instance= new PostDetailsRepositoryImplementation();
        }
        return instance;
    }

    //Post Details
    @Override
    public MutableLiveData<PostStructureModel> postDetails(String id) {
        if (postDetails==null){
            mutablePostDetails = new MutableLiveData<>();
        }

        RetrofitInterface retrofitInterface= RetrofitAPI.getRetrofitInterface();
        Call<PostStructureModel> callData= retrofitInterface.postDetails(id);
        callData.clone().enqueue(new Callback<PostStructureModel>() {
            @Override
            public void onResponse(@NonNull Call<PostStructureModel> call, @NonNull Response<PostStructureModel> response) {
                try {
                    if (response.isSuccessful()){
                        postDetails= response.body();
                        mutablePostDetails.postValue(postDetails);
                    }

                }catch (Exception e){
                    mutablePostDetails.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostStructureModel> call, @NonNull Throwable t) {
                mutablePostDetails.postValue(null);
            }
        });
        return mutablePostDetails;
    }


    //All comments
    @Override
    public MutableLiveData<List<CommentModel>> allComments(String id) {
        if (allComments==null){
            mutableAllComments= new MutableLiveData<>();
        }

        RetrofitInterface retrofitInterface= RetrofitAPI.getRetrofitInterface();
        Call<List<CommentModel>> listOfComment= retrofitInterface.allComments(id);
        listOfComment.clone().enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CommentModel>> call, @NonNull Response<List<CommentModel>> response) {
                try {
                    if (response.isSuccessful()){
                        allComments= response.body();
                        mutableAllComments.postValue(allComments);
                    }
                }catch (Exception e){
                    mutableAllComments.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CommentModel>> call, @NonNull Throwable t) {
                mutableAllComments.postValue(null);
            }
        });

        return mutableAllComments;
    }

}
