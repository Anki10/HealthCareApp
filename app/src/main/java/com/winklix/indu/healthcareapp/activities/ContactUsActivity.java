package com.winklix.indu.healthcareapp.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.winklix.indu.healthcareapp.R;

/**
 * Created by dell on 10-02-2018.
 */

public class ContactUsActivity extends AppCompatActivity {

    private WebView webview;
    private ContactUsActivity ctx = this;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        webview = (WebView) findViewById(R.id.webview_contactUs);

        dialog = new Dialog(this);


        webview.getSettings().setJavaScriptEnabled(true);

        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress >= 85) {
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                }
            }
        });

        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.toLowerCase().startsWith("http") || url.toLowerCase().startsWith("https") || url.toLowerCase().startsWith("file")) {
                    view.loadUrl(url);
                } else {
                    try {
                        Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.d("JSLogs", "Webview Error:" + e.getMessage());
                    }
                }
                return (true);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });

        openURL("https://www.2040healthcare.com/page_description.php?page=5");

//        if (cd.isConnectingToInternet()) {
//
//        } else {
//            if (alertDialog != null && alertDialog.isShowing())
//                alertDialog.dismiss();
//            dialog.displayCommonDialogWithHeader(SaregamaConstants.INTERNET_FAIL, getAppConfigJson().getInternat_fail()+"\n");
//        }
    }

    private void openURL(String url) {
        webview.loadUrl(url);
        webview.requestFocus();

    }

}
