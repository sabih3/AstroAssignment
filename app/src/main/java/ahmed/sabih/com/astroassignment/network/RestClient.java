package ahmed.sabih.com.astroassignment.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sabih Ahmed on 31-Oct-17.
 * ahmed.engr90@gmail.com
 */

public class RestClient {
    private static ServicesInterface servicesInterface;
    private static Retrofit retrofit;
    private static OkHttpClient.Builder httpClient;

    private RestClient(){

    }

    static {
        setUpRestClient();
    }


    private static void setUpRestClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient= new OkHttpClient.Builder();
        httpClient.readTimeout(120, TimeUnit.SECONDS);

        httpClient.addInterceptor(loggingInterceptor);


        retrofit = new Retrofit.Builder()
                .baseUrl(Endpoints.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        servicesInterface = retrofit.create(ServicesInterface.class);
    }


    public static ServicesInterface getAdapter(){


        return servicesInterface;
    }
}
