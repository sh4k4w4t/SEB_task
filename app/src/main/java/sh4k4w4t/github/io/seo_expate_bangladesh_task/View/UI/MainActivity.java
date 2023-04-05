package sh4k4w4t.github.io.seo_expate_bangladesh_task.View.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.WindowManager;

import sh4k4w4t.github.io.seo_expate_bangladesh_task.View.Adapter.AllPostListAdapter;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.ViewModel.AllPostsListViewModel;
import sh4k4w4t.github.io.seo_expate_bangladesh_task.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    AllPostsListViewModel allPostsListViewModel;
    AllPostListAdapter allPostListAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= ActivityMainBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(activityMainBinding.getRoot());


        activityMainBinding.allPostsRecycleView.setHasFixedSize(true);
        activityMainBinding.allPostsRecycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        progressDialogModule();
        allPostsListViewModel= new ViewModelProvider(MainActivity.this).get(AllPostsListViewModel.class);
        allPostsListViewModel.allPosts().observe(MainActivity.this, postStructureModels -> {
            try {
                allPostListAdapter= new AllPostListAdapter(postStructureModels,MainActivity.this);
                activityMainBinding.allPostsRecycleView.setAdapter(allPostListAdapter);
                progressDialog.dismiss();
            }catch (Exception e){
                e.printStackTrace();
                progressDialog.dismiss();
            }
        });

    }

    private void progressDialogModule() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Loading data. Please wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }
}