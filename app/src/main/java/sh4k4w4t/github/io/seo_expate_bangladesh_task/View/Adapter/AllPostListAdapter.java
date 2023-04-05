package sh4k4w4t.github.io.seo_expate_bangladesh_task.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sh4k4w4t.github.io.seo_expate_bangladesh_task.R;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructureModel;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.databinding.CustomLayoutForIndividualPostBinding;

public class AllPostListAdapter extends RecyclerView.Adapter<AllPostListAdapter.CustomViewHolder> {
    List<PostStructureModel> allPostLists;
    Context context;

    public AllPostListAdapter(List<PostStructureModel> allPostLists, Context context) {
        this.allPostLists = allPostLists;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_for_individual_post, parent, false);
        return new CustomViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        PostStructureModel postStructureModel= allPostLists.get(position);
        holder.binding.postTitle.setText(postStructureModel.getTitle().trim());
        try {
            String first50WordsOfBody = postStructureModel.getBody().trim().substring(0, Math.min(postStructureModel.getBody().trim().length(), 150));
            holder.binding.postBody.setText(first50WordsOfBody+".....");
        }catch (Exception e){
            holder.binding.postBody.setText(postStructureModel.getBody().trim()+".....");
        }
        holder.binding.postDetailsLearnMore.setOnClickListener(view -> Toast.makeText(context.getApplicationContext(), "Under Construction..", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        if (allPostLists.isEmpty()) {
            return 0;
        } else {
            return allPostLists.size();
        }
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        CustomLayoutForIndividualPostBinding binding;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= CustomLayoutForIndividualPostBinding.bind(itemView);
        }
    }
}