package sh4k4w4t.github.io.seo_expate_bangladesh_task.View.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.CommentModel;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model.PostStructureModel;
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
        binding= sh4k4w4t.github.io.seo_expate_bangladesh_task.databinding.ActivityPostDeatilsBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        viewModel= new ViewModelProvider(PostDeatilsActivity.this).get(PostDetailsViewModel.class);
        viewModel.postDetails(getIntent().getStringExtra("id")).observe(PostDeatilsActivity.this, postStructureModel -> {
            binding.postTitleId.setText(postStructureModel.getTitle().trim().substring(0,Math.min(postStructureModel.getTitle().length(),25))+"....");
            binding.postDetailsId.setText(postStructureModel.getBody().trim());
        });
        viewModel.allComments(getIntent().getStringExtra("id")).observe(PostDeatilsActivity.this, new Observer<List<CommentModel>>() {
            @Override
            public void onChanged(List<CommentModel> commentModels) {
                binding.allCommentsRecycleView.setHasFixedSize(true);
                binding.allCommentsRecycleView.setLayoutManager(new LinearLayoutManager(PostDeatilsActivity.this));
                allCommentsAdapter= new AllCommentsAdapter(commentModels);
                binding.allCommentsRecycleView.setAdapter(allCommentsAdapter);

            }
        });

        binding.backButton.setOnClickListener(view -> startActivity(new Intent(PostDeatilsActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)));


    }
}