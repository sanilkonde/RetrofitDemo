package com.example.sanil.retrofitexample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;

import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by sanil on 23-08-2017.
 */

public class ApiClient {


    public static final String BASE_URL = "http://192.168.0.122:8080/DemoWebProject/";  //For GET
//    public static final String BASE_URL = "http://192.168.0.163:8080/Axis_SCFM/";  //For GET
//    public static final String BASE_URL = "https://topview.axisbank.co.in/RuralServices/";  //For POST
    private static Retrofit gsonRetrofit = null;
    private static Retrofit stringRetrofit = null;


    static CertificatePinner certPinner = new CertificatePinner.Builder()
            .add("https://topview.axisbank.co.in",
                    "sha256/DH7HuYkH952xy0z3N2WjUD2/M2FsWz3KEWwz/C5OHOg==")
            .build();




    static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .certificatePinner(certPinner)
            .addNetworkInterceptor(new EncryptionInterceptor())
            /*.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .header("User-Agent", "Your-App-Name")
                            .header("Accept", "application/vnd.yourapi.v1.full+json")
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            })*/
            .build();

//    public static OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();

    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static Retrofit getGsonClient() {
        if (gsonRetrofit==null) {
            gsonRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)  //For POST
                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .addConverterFactory(ScalarsConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return gsonRetrofit;
    }

    public static Retrofit getStringClient() {
        if (stringRetrofit==null) {
            stringRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)  //For POST
//                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return stringRetrofit;
    }
}
