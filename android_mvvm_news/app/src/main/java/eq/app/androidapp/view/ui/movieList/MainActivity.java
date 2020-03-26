package eq.app.androidapp.view.ui.movieList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import eq.app.androidapp.R;
import eq.app.androidapp.view.model.NewsArticle;
import eq.app.androidapp.view.model.NewsResponse;
import eq.app.androidapp.view.viewModel.NewsViewModel;

public class MainActivity extends AppCompatActivity {
    ArrayList<NewsArticle> articleArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    NewsViewModel newsViewModel;
    SwipeRefreshLayout swipeRefresh;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_activity);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        getDataNews();
        setupRecyclerView();
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getDataNews();
                        swipeRefresh.setRefreshing(false);
                    }
                }
        );


    }

    private void getDataNews() {
        progressDialog.show();
        newsViewModel.init();
        newsViewModel.getNewsRepository().observe(this, new Observer<NewsResponse>() {
            @Override
            public void onChanged(NewsResponse newsResponse) {
                progressDialog.dismiss();
                swipeRefresh.setRefreshing(false);
                articleArrayList.clear();
                List<NewsArticle> newsArticles = newsResponse.getArticles();
                articleArrayList.addAll(newsArticles);
                newsAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(MainActivity.this, articleArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(newsAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }


}
