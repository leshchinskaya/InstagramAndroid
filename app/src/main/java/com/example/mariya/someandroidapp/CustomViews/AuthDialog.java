package com.example.mariya.someandroidapp.CustomViews;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mariya.someandroidapp.Constants;
import com.example.mariya.someandroidapp.R;
import com.example.mariya.someandroidapp.interfaces.AuthListener;

public class AuthDialog extends Dialog {
    private AuthListener listener;
    private Context context;
    private WebView webView;

    private final String url = Constants.BASE_URL
            + "oath/authorize/?client_id="
            + Constants.INSTAGRAM_CLIENT_ID
            + "&redirect_uri="
            + Constants.INSTAGRAM_REDIRECT_URI
            + "&response_type=token"
            + "&display=touch&scope=public_content";

    public AuthDialog(Context context, AuthListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.auth_dialog);
        initWevView();
    }

    private void initWevView() {
        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){

            String access_token;
            boolean authComplete;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if (url.contains("#access_token=") && !authComplete) {
                    Uri uri = Uri.parse(url);
                    access_token = uri.getEncodedFragment();

                    access_token = access_token.substring(access_token.lastIndexOf("m") + 1);
                    Log.e("access_token", access_token);

                    authComplete = true;

                    listener.onCodeReceived(access_token);

                    dismiss();
                } else if (url.contains("?error")){
                    Log.e("access_token", "getting error fetching access token");
                    Log.e("response", url);
                    dismiss();
                }

            }
        });
    }
}
