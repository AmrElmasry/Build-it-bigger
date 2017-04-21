package app.amrelmasry.joke_display;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by Amr on 21/04/17.
 */

public class JokeDisplayManager {

    public static void displayJoke(@NonNull Context context, @NonNull String joke) {
        Intent intent = new Intent(context, JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.JOKE_EXTRA, joke);
        context.startActivity(intent);
    }
}
