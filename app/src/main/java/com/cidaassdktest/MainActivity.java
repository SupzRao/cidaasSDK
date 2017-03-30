package com.cidaassdktest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cidaassdk.CidaasConstants;
import com.cidaassdk.CidaasSDK;
import com.cidaassdk.Icallback_;
import com.cidaassdk.ResponseEntity;

public class MainActivity extends AppCompatActivity {
    static CidaasSDK cidaasHelper = null;
    RelativeLayout layout_root;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout_root = (RelativeLayout) findViewById(R.id.layout_root);
        logout = (Button) findViewById(R.id.logout);
        getView();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CidaasSDK.logoutUser("57511b982ab79c0001a44232", getApplicationContext());
            }
        });
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
        cidaasHelper.setUserIdURL("https://apis.test.carbookplus.com/oauth2-usermanagement/oauth2/userinfo");
        cidaasHelper.setViewType(CidaasConstants.VIEW_TYPE);
        cidaasHelper.setLogoutURL("https://apis.test.carbookplus.com/oauth2-usermanagement/oauth2/logoutuser");
        /*
        * create call back to get access token and show
        * */
        cidaasHelper.callback_ = new Icallback_() {
            @Override
            public void getLoginResponse(ResponseEntity entity) {
                Toast.makeText(MainActivity.this, entity.getAccess_token(), Toast.LENGTH_SHORT).show();
                //    CidaasSDK.getUserDetailsByAccessToken(entity.getAccess_token(), getApplicationContext());

            }
        };
        cidaasHelper.loadView(layout_root, getApplicationContext());
        // CidaasSDK.getAccessTokenByUserId("57511b982ab79c0001a44232");

    }
}
