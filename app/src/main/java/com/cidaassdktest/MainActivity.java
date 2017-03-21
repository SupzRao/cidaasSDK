package com.cidaassdktest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cidaassdk.CidaasConstants;
import com.cidaassdk.CidaasSDK;
import com.cidaassdk.Icallback_;

public class MainActivity extends AppCompatActivity {
    static CidaasSDK cidaasHelper = null;
    RelativeLayout layout_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout_root= (RelativeLayout) findViewById(R.id.layout_root);
        getView();
    }

    CidaasSDK getCidaasSDKInst() {
        if (cidaasHelper == null) {
            cidaasHelper = new CidaasSDK(getApplicationContext());
        }
        return cidaasHelper;
    }

    private void getView() {
    /*
    * set all the url required for loading a login view
    * */
        cidaasHelper = getCidaasSDKInst();
        cidaasHelper.setAuthorizationURL("https://apis-cidaas.test.carbookplus.com/oauth2-login/oauth2/authz");
        cidaasHelper.setTokenURL("https://apis-cidaas.test.carbookplus.com/oauth2-login/oauth2/token");
        cidaasHelper.setGrantType(CidaasConstants.GRANT_TYPE);
        cidaasHelper.setClientSecret("6925369134149551229");
        cidaasHelper.setClientId("74af56f6dc104bd6bb6d145f1ba0665d");
        cidaasHelper.setRedirectURI("https://apis.test.carbookplus.com/user-ui/html/calback.html");
        cidaasHelper.setResponseType(CidaasConstants.RESPONSE_TYPE);
        cidaasHelper.setViewType(CidaasConstants.VIEW_TYPE);
        /*
        * create call back to get access token and show
        * */
        cidaasHelper.callback_ = new Icallback_() {
            @Override
            public void printMessage(String msg) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        cidaasHelper.loadView(layout_root, getApplicationContext());
    }
}
