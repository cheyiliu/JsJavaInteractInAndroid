package com.example.jsjavainteractinandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private Button mBtnJavaCallJsWithoutParam;
    private Button mBtnJavaCallJsWithParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(this, "jsbridge");
        mWebView.loadUrl("file:///android_asset/demo_java_js_call.html");

        mBtnJavaCallJsWithParam = (Button) findViewById(R.id.button_java_call_js_with_param);
        mBtnJavaCallJsWithParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String param = "'a param from java'";//注意引号
                mWebView.loadUrl("javascript:java_call_js_with_param("
                                + param
                                + ")"
                );
            }
        });

        mBtnJavaCallJsWithoutParam = (Button) findViewById(R.id.button_java_call_js_without_param);
        mBtnJavaCallJsWithoutParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:java_call_js_without_param()");
            }
        });
    }

    @JavascriptInterface
    public void callFromJs() {
        Toast.makeText(this, "callFromJs, 无参", Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void callFromJsWithParam(String param) {
        Toast.makeText(this, "callFromJs, 参数是：" + param, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
