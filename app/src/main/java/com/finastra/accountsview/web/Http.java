package com.finastra.accountsview.web;

//Http.java
import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Http {
    public static String getAccessToken(String clientId, String clientSecret) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Utils.tokenUrl)
                .build();

        AccessTokenServiceInterface service = retrofit.create(AccessTokenServiceInterface.class);

        //grant types = client_credentials
        Call<TokenResponse> call = service.getToken(clientId, clientSecret, "client_credentials");
        try {
            Response<TokenResponse> response = call.execute();
            Log.d("Product", response.message()+": "+getTokenType(response.body().getTokenType())+" "+response.body().getAccessToken());
            return getTokenType(response.body().getTokenType())+" "+response.body().getAccessToken();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getTokenType(String tokenType) {
        // OAuth requires uppercase Authorization HTTP header value for token type
        if (! Character.isUpperCase(tokenType.charAt(0))) {
            tokenType =
                    Character
                            .toString(tokenType.charAt(0))
                            .toUpperCase() + tokenType.substring(1);
        }

        return tokenType;
    }
}
