package sh4k4w4t.github.io.seo_expate_bangladesh_task.View.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.android.volley.RequestQueue;

import java.util.List;
import java.util.Objects;

import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.Comment;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Repository.PostDetailsRepositoryImplementation;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.View.Adapter.AllCommentsAdapter;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.ViewModel.PostDetailsViewModel;

public class PostDeatilsActivity extends AppCompatActivity {
    sh4k4w4t.github.io.seo_expate_bangladesh_task.databinding.ActivityPostDeatilsBinding binding;
    PostDetailsViewModel viewModel;
    AllCommentsAdapter allCommentsAdapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = sh4k4w4t.github.io.seo_expate_bangladesh_task.databinding.ActivityPostDeatilsBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(PostDeatilsActivity.this).get(PostDetailsViewModel.class);
        viewModel.postDetails(getIntent().getStringExtra("id")).observe(PostDeatilsActivity.this, postStructureModel -> {
            binding.postTitleId.setText(postStructureModel.getTitle().trim().substring(0, Math.min(postStructureModel.getTitle().length(), 25)) + "....");
            binding.postDetailsId.setText(postStructureModel.getBody().trim());
        });
        viewModel.allComments(getIntent().getStringExtra("id")).observe(PostDeatilsActivity.this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                binding.allCommentsRecycleView.setHasFixedSize(true);
                binding.allCommentsRecycleView.setLayoutManager(new LinearLayoutManager(PostDeatilsActivity.this));
                allCommentsAdapter = new AllCommentsAdapter(comments);
                binding.allCommentsRecycleView.setAdapter(allCommentsAdapter);
            }
        });

        binding.submitCommentInputLayout.setEndIconOnClickListener(view -> {
            String token = "e3b2d0c34b84c2774106ebaa4946e7b5b7ef0cc64a68036025820d4a8c3cc8a9";
            PostDetailsRepositoryImplementation implementation = new PostDetailsRepositoryImplementation();
            implementation.postComment(getIntent().getStringExtra("id") + "", token, "Unknown Person", "unknown@email.com", Objects.requireNonNull(binding.textComment.getText()).toString().trim() + "");
        });

        binding.backButton.setOnClickListener(view -> startActivity(new Intent(PostDeatilsActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)));
    }

}