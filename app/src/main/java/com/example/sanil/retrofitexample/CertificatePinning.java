package com.example.sanil.retrofitexample;

import java.io.IOException;
import java.security.cert.Certificate;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sanil on 04-11-2017.
 */

public class CertificatePinning {

    OkHttpClient client;

    public CertificatePinning() {
        client = new OkHttpClient.Builder()
                .certificatePinner(new CertificatePinner.Builder()
                        .add("https://topview.axisbank.co.in", "sha256/109ad7ed77f64bde862774f917a50b9bf14a7f6d0158ea461e1699498d9c92be=")
                        .build())
                .build();
    }

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("https://publicobject.com/robots.txt")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        for (Certificate certificate : response.handshake().peerCertificates()) {
            System.out.println(CertificatePinner.pin(certificate));
        }
    }
}
