package com.example.amr.builditbigger.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class Joke {

    private String joke;

    public Joke(String joke) {
        this.joke = joke;
    }


    public String getJoke() {
        return joke;
    }
}