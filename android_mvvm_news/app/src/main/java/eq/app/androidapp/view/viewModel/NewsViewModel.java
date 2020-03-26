package eq.app.androidapp.view.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import eq.app.androidapp.view.model.NewsResponse;
import eq.app.androidapp.view.repository.NewsRepository;


public class NewsViewModel extends ViewModel {

    private MutableLiveData<NewsResponse> mutableLiveData;
    private NewsRepository newsRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        newsRepository = NewsRepository.getInstance();
        mutableLiveData = newsRepository.getNews("google-news", "ad932c4cf6c341bda21a6760aeb94641");

    }

    public LiveData<NewsResponse> getNewsRepository() {
        return mutableLiveData;
    }





}
