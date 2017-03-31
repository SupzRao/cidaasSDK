package com.cidaassdktest;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cidaassdk.CidaasSDK;
import com.cidaassdk.ResponseEntity;
import com.cidaassdk.UserProfile;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    static CidaasSDK cidaasHelper = null;
    RelativeLayout layout_root;
    Button logout, usrProf;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TextView textView_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sp.edit();
        layout_root = (RelativeLayout) findViewById(R.id.layout_root);
        logout = (Button) findViewById(R.id.logout);
        usrProf = (Button) findViewById(R.id.usrProf);
        textView_user = (TextView) findViewById(R.id.textView_user);
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("properties.xml");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        cidaasHelper = CidaasSDK.getCidaasSDKInst(getApplicationContext());
        cidaasHelper.setURLFile(inputStream);
        cidaasHelper.callback_ = new CidaasSDK.Icallback_() {
            @Override
            public void getResponse(ResponseEntity entity) {
                editor.putString("Access_Token", entity.getAccess_token());
                editor.commit();
                Toast.makeText(MainActivity.this, entity.getAccess_token(), Toast.LENGTH_SHORT).show();
            }
        };
        cidaasHelper.login(layout_root, getApplicationContext());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CidaasSDK.logout("5774adcdb3cb1f0001ad2e4a", getApplicationContext());
            }
        });
        final CidaasSDK.UserProfCallback userProfCallback = new CidaasSDK.UserProfCallback() {
            @Override
            public void onSuccess(UserProfile userProfile) {
                if (userProfile!=null) {
                    textView_user.setText("ID: "+userProfile.getId()+" Name : "+userProfile.getUsername());
                }
            }
            @Override
            public void onError(String message) {

            }
        };
        usrProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String AccessToken = sp.getString("Access_Token", "");
                CidaasSDK.getUserInfoFromAccessToken(userProfCallback,AccessToken, getApplicationContext());

            }
        });
    }


    private void getView() {

    }
}
