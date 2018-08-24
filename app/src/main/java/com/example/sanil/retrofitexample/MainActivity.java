package com.example.sanil.retrofitexample;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//interface Addable{
//    //int add(int a,int b);
//    String abc(String s);
//
//
//}
//
//abstract class abc
//{
//    abstract void run();
//}
//
//abstract class abc1 extends abc
//{
//    abstract void run1();
//}
//
//interface ab extends Addable
//{
//
//}

public class MainActivity extends Activity {

    public static String TAG = "retrofit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Addable person = (message)-> {
//            String str1 = "I would like to say, ";
//            String str2 = message + str1;
//            return str2;
//        };

        // ApiInterface apiInterface = (c)->(c);

//        Log.i(TAG,"");
//
//        Log.i(TAG,"addtion is "+person.abc("Hey,"));

        final ApiInterface apiServiceForGsonResponse = ApiClient.getGsonClient().create(ApiInterface.class);
        final ApiInterface apiServiceForStringResponse = ApiClient.getStringClient().create(ApiInterface.class);


//        findViewById(R.id.getButton).setOnClickListener(view ->
//            Toast.makeText(getApplicationContext(),"Hello !!!",Toast.LENGTH_SHORT).show()
//        );

        findViewById(R.id.getButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<String> call = apiServiceForStringResponse.getList();
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String>call, Response<String> response) {

                        Log.i(TAG,"response is "+response.body());
                        Log.i(TAG,"response code is "+response.code());
                    }

                    @Override
                            public void onFailure(Call<String>call, Throwable t) {
                                // Log error here since request failed
                                Log.i(TAG, t.toString());
                            }
                });
//                Observable<List<ModelGET>> getMethod = apiServiceForGsonResponse.savePost();
//
//                getMethod.subscribeOn(Schedulers.io())
//                        .subscribe(new Observer<List<ModelGET>>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(List<ModelGET> value) {
//
//                                Log.i(TAG,"in onNext "+value.size());
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                                Log.i(TAG,"in onerror "+e);
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                                Log.i(TAG,"in Complete");
//                            }
//                        });
//
            }
        });

        findViewById(R.id.postButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject jsonObject = new JSONObject();


                try
                {
//                    jsonObject.put("Username","E1102");
//                    jsonObject.put("Password","1234");

                    jsonObject.put("loginId","ThreeIMMaker");
                    jsonObject.put("action","verify_user");
                    jsonObject.put("otp","");
                    jsonObject.put("mPin","");
                    jsonObject.put("adUser","NO");
                    jsonObject.put("IMEINumber","351962070895178");
                    jsonObject.put("cropId","");
                    jsonObject.put("password","Axis@1234");

                }
                catch (Exception ex){}

//                Log.i(TAG,"input json for post service is "+jsonObject.toString());

                Call<ModelArrayPOST> call = apiServiceForGsonResponse.login("A1/8TllXBUIvuJzThNuj5FCUSAQBfkZtuw0qu5T823hotZqOz4uM9DPQRT2Zq7J9D8VUFnbSYYor");
                call.enqueue(new Callback<ModelArrayPOST>() {
                    @Override
                    public void onResponse(Call<ModelArrayPOST>call, Response<ModelArrayPOST> response) {


                        Log.i(TAG,"response is "+response);
                        Log.i(TAG,"response code is "+response.code());
                    }

                    @Override
                    public void onFailure(Call<ModelArrayPOST>call, Throwable t) {
                        // Log error here since request failed
                        Log.i(TAG, "in onFailure1 "+call.toString());
                        Log.i(TAG, "in onFailure2 "+t.toString());
                    }
                });

                Log.i(TAG,"Post Button Clicked!!  "+Encryption.encryptString(jsonObject.toString()));

//                Observable<ModelArrayPOST> login = apiServiceForGsonResponse.login("A1/8TllXBUIvuJzThNuj5FCUSAQBfkZtuw0qu5T823hotZqOz4uM9DPQRT2Zq7J9D8VUFnbSYYor");
//
//                login.subscribeOn(Schedulers.io())
//                        .subscribe(new Observer<ModelArrayPOST>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ModelArrayPOST value) {
//
//                        Log.i(TAG,"in onNext "+value.getUserID());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                        Log.i(TAG,"in onError "+e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                        Log.i(TAG,"in Complete!!!");
//
//                    }
//                });

//                Observable<String> modelPostObjectObservable = apiServiceForStringResponse.verifyUser(Encryption.encryptString(jsonObject.toString()));
//
//                modelPostObjectObservable.subscribeOn(Schedulers.io()).subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String value) {
//
//                        Log.i(TAG,"in onNext "+value);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                        Log.i(TAG,"in onError "+e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


//                Observable<ModelPostObject> modelPostObjectObservable = apiServiceForStringResponse.verifyUserForModel(jsonObject.toString());
//
//                modelPostObjectObservable.subscribeOn(Schedulers.io()).subscribe(new Observer<ModelPostObject>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ModelPostObject value) {
//
//                        Log.i(TAG,"in onNext "+value.getMessage());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                        Log.i(TAG,"in onError "+e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
            }
        });
    }


}
