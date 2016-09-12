package com.example.natkinson.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.basecamp.turbolinks.TurbolinksAdapter;
import com.basecamp.turbolinks.TurbolinksSession;
import com.basecamp.turbolinks.TurbolinksView;

public class MainActivity extends AppCompatActivity implements TurbolinksAdapter {

    private static final String INTENT_URL = "https://acadmi-staging-pr-109.herokuapp.com/";
    private static final String BASE_URL = "https://acadmi-staging-pr-109.herokuapp.com/";
    private String location;
    private TurbolinksView turbolinksView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turbolinksView = (TurbolinksView) findViewById(R.id.turbolinks_view);

        location = (getIntent().getStringExtra(INTENT_URL) != null) ? getIntent().getStringExtra(INTENT_URL) : BASE_URL;

        TurbolinksSession turbolinksSession = TurbolinksSession.getDefault(this);
//        turbolinksSession.setScreenshotsEnabled(true);
//        turbolinksSession.getWebView().getSettings().setJavaScriptEnabled(true);
//        turbolinksSession.getWebView().getSettings().setDatabaseEnabled(true);
//        turbolinksSession.getWebView().getSettings().setDomStorageEnabled(true);

        turbolinksSession
            .activity(this)
            .adapter(this)
            .view(turbolinksView)
            .visit(location);

        Log.w("AHHHHHHHHHHH", "onCreate");
    }

    @Override
    public void onPageFinished() {
        Log.w("AHHHHHHHHHHH", "onPageFinished");
    }

    @Override
    public void onReceivedError(int errorCode) {
        Log.w("AHHHHHHHHHHH", "onReceivedError");
    }

    @Override
    public void pageInvalidated() {
        Log.w("AHHHHHHHHHHH", "pageInvalidated");
    }

    @Override
    public void requestFailedWithStatusCode(int statusCode) {
        Log.w("AHHHHHHHHHHH", "requestFailedWithStatusCode");
    }

    @Override
    public void visitCompleted() {
        Log.w("AHHHHHHHHHHH", "visitCompleted");
    }

    @Override
    public void visitProposedToLocationWithAction(String location, String action) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(INTENT_URL, location);
        this.startActivity(intent);

        Log.w("AHHHHHHHHHHH", "visitProposedToLocationWithAction");
    }

    @Override
    protected void onRestart() {

        super.onRestart();

        TurbolinksSession.getDefault(this)
            .activity(this)
            .adapter(this)
            .restoreWithCachedSnapshot(true)
            .view(turbolinksView)
            .visit(location);

        Log.w("AHHHHHHHHHHH", "onRestart");
    }
}
