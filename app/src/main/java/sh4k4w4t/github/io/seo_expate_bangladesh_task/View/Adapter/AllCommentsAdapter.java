package sh4k4w4t.github.io.seo_expate_bangladesh_task.View.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sh4k4w4t.github.io.seo_expate_bangladesh_task.R;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.Comment;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.databinding.CustomCommentLayoutBinding;

public class AllCommentsAdapter extends RecyclerView.Adapter<AllCommentsAdapter.CustomViewHolder> {
    List<Comment> allComments;

    public AllCommentsAdapter(List<Comment> allComments) {
        this.allComments = allComments;
    }

    @NonNull
    @Override
    public AllCommentsAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_comment_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCommentsAdapter.CustomViewHolder holder, int position) {
        Comment comment = allComments.get(position);
        holder.binding.commentTitle.setText(comment.getName().trim());
        holder.binding.commentDetails.setText(comment.getBody().trim());
        Log.d("TAG", "onBindViewHolder: " + comment);
    }

    @Override
    public int getItemCount() {
        if (allComments.isEmpty()) {
            return 0;
        } else {
            return allComments.size();
        }
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        CustomCommentLayoutBinding binding;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomCommentLayoutBinding.bind(itemView);
        }
    }
}
