package app.amrelmasry.joke_display;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "app.amrelmasry.joke_display.joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        showUpButton();

        String joke = getIntent().getStringExtra(JOKE_EXTRA);
        if (joke == null) {
            throw new IllegalStateException("You must send quote extra to be shown");
        }
        showJoke(joke);
    }

    private void showUpButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showJoke(String joke) {
        TextView jokeTv = (TextView) findViewById(R.id.joke_tv);
        jokeTv.setText(joke);
    }
}
