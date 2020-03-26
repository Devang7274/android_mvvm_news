package eq.app.androidapp.view.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import eq.app.androidapp.view.model.NewsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static NewsRepository newsRepository;

    public static NewsRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private NewsApi newsApi;

    private NewsRepository(){
        newsApi = RetrofitService.cteateService(NewsApi.class);
    }

    public MutableLiveData<NewsResponse> getNews(String source, String key){
        final MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        newsApi.getNewsList(source, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call,
                                   Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    Log.e("TAG", "onResponse: True");
                    newsData.setValue(response.body());
                }else {
                    Log.e("TAG", "onResponse: False");
                    newsData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("TAG", "onFailure: ");
                newsData.setValue(null);
            }
        });
        return newsData;
    }


}