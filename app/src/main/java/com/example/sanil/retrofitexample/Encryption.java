package com.example.sanil.retrofitexample;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption
{

    private static final String TAG_ENCRYPTION = "Encrpytion";
    private static final String characterEncoding = "UTF-8";
    private static final String cipherTransformation = "AES/CBC/PKCS5Padding";
    private static final String aesEncryptionAlgorithm = "AES";
    static Context context;

    public Encryption(Context context)
    {
        this.context = context;
    }
    // private static final String key = "1234";


    public static String encryptString(String s)
    {
        Log.i(MainActivity.TAG,"In encryption ");
        String mLog = "None";
        try
        {
            byte[] plainTextbytes = s.getBytes(characterEncoding);
            byte[] keyBytes = getKeyBytes("axisbankaxisbank54321");
            return Base64.encodeToString(encrypt(plainTextbytes,keyBytes, keyBytes), Base64.DEFAULT);
        }
        catch (UnsupportedEncodingException u)
        {
            mLog = u.getMessage();
        }
        catch (InvalidKeyException i)
        {
            mLog = i.getMessage();
        }
        catch (NoSuchAlgorithmException n)
        {
            mLog = n.getMessage();
        }
        catch (NoSuchPaddingException p)
        {
            mLog = p.getMessage();
        }
        catch (InvalidAlgorithmParameterException ip)
        {
            mLog = ip.getMessage();
        }
        catch (IllegalBlockSizeException b)
        {
            mLog = b.getMessage();
        }
        catch (BadPaddingException bp)
        {
            mLog = bp.getMessage();
        }
        catch (Exception e)
        {
            mLog = e.getMessage();
        }
        Log.i(MainActivity.TAG,"encyrptString() -> Input : " + s+" Output : " + s + " Exception : " + mLog);
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

    private static String decryptString(String url, String encryptedText)
    {
        String mLog="None";

        try
        {
            byte[] cipheredBytes = Base64.decode(encryptedText, Base64.DEFAULT);
            byte[] keyBytes = getKeyBytes("1234");

            String decryptedString = new String(decrypt(cipheredBytes, keyBytes,keyBytes), characterEncoding);
            //Log.i(TAG_ENCRYPTION,"decryptString() ->Url :"+url+"\nInput : " + encryptedText+" Output : " + decryptedString + " Exception : " + mLog);

            return decryptedString;
        }
        catch (Exception e)
        {
            mLog = e.getMessage();
        }

        //Log.i(TAG_ENCRYPTION,"decryptString() ->Url :"+url+"\nInput : " + encryptedText+" Output : " + encryptedText + " Exception : " + mLog);

        return encryptedText;
    }
    
    public static String decryptString(String encryptedText)
    {

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

}
