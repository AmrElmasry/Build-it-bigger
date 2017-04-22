package com.udacity.gradle.builditbigger.network;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.amr.builditbigger.backend.myApi.MyApi;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.lang.ref.WeakReference;

import app.amrelmasry.joke_display.JokeDisplayManager;

/**
 * Created by Amr on 21/04/17.
 */

public class JokeAsyncTask extends AsyncTask<Void, Void, String> {
    private MyApi mMyApiService;
    private WeakReference<Context> mContext;

    public JokeAsyncTask(Context mContext) {
        this.mContext = new WeakReference<>(mContext);
    }

    @Override
    protected String doInBackground(Void... params) {
        if (mMyApiService == null) {
            mMyApiService = initApiService();
        }

        String joke = null;
        try {
            MyApi.SayJoke sayJoke = mMyApiService.sayJoke();
            joke = sayJoke.execute().getJoke();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return joke;
    }

    @Override
    protected void onPostExecute(String joke) {
        if (mContext.get() != null && joke != null) {
            showJoke(joke);
        }
    }

    private void showJoke(@NonNull String joke) {
        JokeDisplayManager.displayJoke(mContext.get(), joke);
    }

    private MyApi initApiService() {
        // AndroidHttp.newCompatibleTransport()
        MyApi.Builder builder = new MyApi.Builder(new ApacheHttpTransport(),
                new JacksonFactory(), null)
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        return builder.build();
    }
}
