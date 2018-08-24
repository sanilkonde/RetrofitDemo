package com.example.sanil.retrofitexample;


import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by sanil on 12-01-2018.
 */

public class EncryptionInterceptor implements Interceptor {

    static String characterEncoding = "UTF-8";
    private static final String TAG = EncryptionInterceptor.class.getSimpleName();
    private static final String cipherTransformation = "AES/CBC/PKCS5Padding";
    private static final String aesEncryptionAlgorithm = "AES";

    private static final boolean DEBUG = true;




    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        RequestBody oldBody = request.body();

        Buffer buffer = new Buffer();
        oldBody.writeTo(buffer);


        String strOldBody = buffer.readUtf8();
        Log.i(MainActivity.TAG,"original req "+strOldBody);
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String strNewBody = "data="+Encryption.encryptString(URLDecoder.decode(strOldBody).replace("data=",""));//EncryptionInterceptor.encryptString(strOldBody);

        Log.i(MainActivity.TAG,"strNewBody "+strNewBody);
        RequestBody body = RequestBody.create(mediaType, strNewBody);

        Log.i(MainActivity.TAG,"content type is "+body.contentType().toString());
        Log.i(MainActivity.TAG,"content length is "+String.valueOf(body.contentLength()));
        Log.i(MainActivity.TAG,"method is "+request.method());

        request = request.newBuilder().header("Content-Type", body.contentType().toString())
                .header("Content-Length", String.valueOf(body.contentLength()))
                .method(request.method(), body).build();

//        Response response = chain.proceed(request);
//        if(response.code() == 200)
//        {
//            MediaType contentType = response.body().contentType();
//            Log.i(MainActivity.TAG,"response of body is "+response.body());
//
//            ResponseBody body1 = ResponseBody.create(contentType, response.body().);gl8759767
//            //return response.newBuilder().body(body1).build();
//        }

        return chain.proceed(request);
    }


    public static String decryptString(String encryptedText)
    {

        Log.i(MainActivity.TAG,"decryptString res "+encryptedText);
        try
        {
            byte[] cipheredBytes = Base64.decode(encryptedText, Base64.DEFAULT);
            byte[] keyBytes = getKeyBytes("axisbankaxisbank54321");
            String decryptedString = new String(decrypt(cipheredBytes, keyBytes,keyBytes), characterEncoding);

            return decryptedString;
        }

        catch (Exception e)
        {
            Log.e(MainActivity.TAG,"decryptString Exception : "+e);
        }

        // //Log.i(TAG_ENCRYPTION,"decryptString() ->Input : " + encryptedText+" Output : " + encryptedText + " Exception : " + mLog);

        return encryptedText;
    }

    private static byte[] decrypt(byte[] cipherText, byte[] key,byte[] initialVector) throws Exception
    {
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpecy = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy,ivParameterSpec);
        cipherText = cipher.doFinal(cipherText);
        return cipherText;
    }

    private static String encryptString(String  s){
        //your code

        Log.i(MainActivity.TAG,"in encryptString "+s);
        try
        {
            byte[] plainTextbytes = s.getBytes(characterEncoding);
            byte[] keyBytes = getKeyBytes("axisbankaxisbank54321");
            return Base64.encodeToString(encrypt(plainTextbytes,keyBytes, keyBytes), Base64.DEFAULT);
        }
        catch (Exception e)
        {
            Log.i(MainActivity.TAG,"in encryptString ex "+e);
        }
        Log.i(MainActivity.TAG,"encyrptString() -> Input : " + s+" Output : " + s);
        return s.replace("\n","");
    }

    private static byte[] getKeyBytes(String key) throws UnsupportedEncodingException
    {
        byte[] keyBytes= new byte[16];
        byte[] parameterKeyBytes= key.getBytes(characterEncoding);
        System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
        return keyBytes;
    }

    private static byte[] encrypt(byte[] plainText, byte[] key, byte [] initialVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        plainText = cipher.doFinal(plainText);
        return plainText;
    }
}
