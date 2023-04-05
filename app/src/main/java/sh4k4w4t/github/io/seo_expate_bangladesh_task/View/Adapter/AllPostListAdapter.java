package sh4k4w4t.github.io.seo_expate_bangladesh_task.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sh4k4w4t.github.io.seo_expate_bangladesh_task.R;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructure;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.View.UI.PostDeatilsActivity;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.databinding.CustomLayoutForIndividualPostBinding;

public class AllPostListAdapter extends RecyclerView.Adapter<AllPostListAdapter.CustomViewHolder> {
    List<PostStructure> allPostLists;
    Context context;

    public AllPostListAdapter(List<PostStructure> allPostLists, Context context) {
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
        PostStructure postStructure = allPostLists.get(position);
        holder.binding.postTitle.setText(postStructure.getTitle().trim());
        try {
            String first50WordsOfBody = postStructure.getBody().trim().substring(0, Math.min(postStructure.getBody().trim().length(), 150));
            holder.binding.postBody.setText(first50WordsOfBody + ".....");
        } catch (Exception e) {
            holder.binding.postBody.setText(postStructure.getBody().trim() + ".....");
        }
        holder.binding.postDetailsLearnMore.setOnClickListener(view -> {
            Intent i = new Intent(context.getApplicationContext(), PostDeatilsActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("id", postStructure.getId().toString().trim());
            context.startActivity(i);
        });
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
            binding = CustomLayoutForIndividualPostBinding.bind(itemView);
        }
    }
}
