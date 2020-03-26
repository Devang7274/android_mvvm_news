package eq.app.androidapp.view.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitService {

    //http://newsapi.org/v2/everything?q=bitcoin&apiKey=ad932c4cf6c341bda21a6760aeb94641

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
