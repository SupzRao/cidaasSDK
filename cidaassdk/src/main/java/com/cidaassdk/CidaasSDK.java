package com.cidaassdk;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.SslError;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scottyab.aescrypt.AESCrypt;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Suprada on 08-Mar-17.
 */

public class CidaasSDK extends RelativeLayout {
    private static final String TAG = "TAG";
    private RelativeLayout content;
    ProgressBar progressBar;
    private static WebView webview_ = null;
    private static boolean error_ = false;
    String internetError = "";
    private static SharedPreferences sp = null;
    private String authorizationURL;
    private static String tokenURL;
    private static String logoutURL;
    private static String clientId;
    private static String clientSecret;
    private static String grantType;
    private static String responseType;
    private View view;
    private static String redirectURI;
    public static Icallback_ callback_;
    private static String viewType;
    private static String error_description = "";
    private static String userIdURL;
    static SharedPreferences.Editor editor;
    private Document propertiesXML;
    static CidaasSDK cidaasHelper;

    public CidaasSDK(Context context) {
        super(context);
        init(context);
    }

    public CidaasSDK(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private WebView getInstanceOfWebview(Context context) {
        if (webview_ == null) {
            webview_ = new WebView(context);
        }
        return webview_;
    }
    private static String getUserIdURL() {
        if (userIdURL == null)
            return "";
        else
            return userIdURL;
    }
    private String getViewType() {
        if (viewType == null)
            return "";
        else
            return viewType;
    }

    private static String getLogoutURL() {
        if (logoutURL == null)
            return "";
        else
            return logoutURL;
    }


    private String getClientId() {
        if (clientId == null)
            return "";
        else
            return clientId;
    }


    private String getRedirectURI() {
        if (redirectURI == null)
            return "";
        else
            return redirectURI;
    }


    private String getResponseType() {
        if (responseType == null)
            return CidaasConstants.RESPONSE_TYPE;
        else
            return responseType;
    }

    private String getAuthorizationURL() {
        if (authorizationURL == null)
            return "";
        else
            return authorizationURL;
    }


    private String getTokenURL() {
        if (tokenURL == null)
            return "";
        else
            return tokenURL;
    }


    private String getClientSecret() {
        if (clientSecret == null)
            return "";
        else
            return clientSecret;
    }


    private String getGrantType() {
        if (grantType == null)
            return CidaasConstants.GRANT_TYPE;
        else
            return grantType;
    }


    /*
    * Loading view WebView dynamically so that loads generated URL
    *
    * */
    public void login(final RelativeLayout layout, final Context context) {
        /*
        * remove all the previous view in custom layout
        * */
        // layout.removeAllViews();
        try {
            String errorMsg = "";
            if (CidaasConstants.isInternetAvailable(context)) {
                if (getAuthorizationURL().equals("")) {
                    errorMsg = "AuthorizationURL Missing";
                    getErrorImage(layout, context, errorMsg);

                } else if (getRedirectURI().equals("")) {
                    errorMsg = "RedirectURI Missing";
                    getErrorImage(layout, context, errorMsg);

                } else if (getResponseType().equals("")) {
                    errorMsg = "ResponseType Missing ";
                    getErrorImage(layout, context, errorMsg);

                } else if (getClientId().equals("")) {
                    errorMsg = "ClientId Missing ";
                    getErrorImage(layout, context, errorMsg);

                } else if (getViewType().equals("")) {
                    errorMsg = "ViewType Missing ";
                    getErrorImage(layout, context, errorMsg);

                } else if (getClientSecret().equals("")) {
                    errorMsg = "ClientSecret Missing ";
                    getErrorImage(layout, context, errorMsg);
                } else if (getTokenURL().equals("")) {
                    errorMsg = "TokenURL Missing ";
                    getErrorImage(layout, context, errorMsg);
                } else if (getGrantType().equals("")) {
                    errorMsg = "GrantType Missing ";
                    getErrorImage(layout, context, errorMsg);
                } else if (getUserIdURL().equals("")) {
                    errorMsg = "User ID URL Missing ";
                    getErrorImage(layout, context, errorMsg);
                } else if (getLogoutURL().equals("")) {
                    errorMsg = "Logout URL Missing ";
                    getErrorImage(layout, context, errorMsg);
                } else {
                    String myUrl = constructURL();
                    webview_ = getInstanceOfWebview(context);
                    WebSettings settings = webview_.getSettings();
                    settings.setJavaScriptEnabled(true);
                    CookieSyncManager.createInstance(context);
                    CookieManager cookieManager = CookieManager.getInstance();
                    cookieManager.removeAllCookie();
                    webview_.loadUrl(myUrl);
                    ProgressBar progressBar = new ProgressBar(context);
                    webview_.addJavascriptInterface(new BaseJavaScriptInterface(context), "NativeMsgForwarder");
                    settings.setSavePassword(false);
                    settings.setJavaScriptCanOpenWindowsAutomatically(true);
                    settings.setSupportMultipleWindows(true);
                    settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
                    webview_.setWebViewClient(new BaseWebViewClient(progressBar, context, callback_, getTokenURL(), getClientId(),
                            getRedirectURI(), getClientSecret(), getGrantType(), layout));
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                /*
                * adding second element progress bar overlapping webview
                * */
                    RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams
                            (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params1.addRule(RelativeLayout.CENTER_IN_PARENT, webview_.getId());
                    layout.addView(webview_, params);
                    layout.addView(progressBar, params1);
                }

            } else {
                internetError = "Try Again";
                getInternetErrorImage(layout, context, internetError);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void getInternetErrorImage(final RelativeLayout layout, final Context context, String internet_error_msg) {
        ImageView image;
        image = new ImageView(context);
        image.setImageDrawable(context.getResources().getDrawable(R.drawable.no_internet_bg));
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        Button button = new Button(context);
        button.setText(internet_error_msg);
        button.setPadding(5, 5, 5, 5);
        button.setTextColor(Color.BLACK);
        button.setBackgroundColor(Color.LTGRAY);
        LayoutParams params1 = new LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, image.getId());
        params1.addRule(RelativeLayout.CENTER_IN_PARENT, image.getId());
        params1.setMargins(5, 5, 5, 100);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                login(layout, context);
            }
        });
        layout.addView(button, params1);
        layout.addView(image, params);
    }

    void getErrorImage(RelativeLayout layout, Context context, String errorMsg) {
        ImageView image = new ImageView(context);
        image.setImageDrawable(context.getResources().getDrawable(R.drawable.settings));
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        TextView textViewError = new TextView(context);
        textViewError.setText(errorMsg);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.addRule(RelativeLayout.CENTER_IN_PARENT, image.getId());
        params1.addRule(RelativeLayout.ALIGN_PARENT_TOP, image.getId());
        textViewError.setPadding(5, 100, 5, 0);
        textViewError.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textViewError.setTextColor(Color.BLACK);
        layout.addView(textViewError, params1);
        layout.addView(image, params);
    }

    /*
    *
    * generate URL by given redirectURI and client id
    *
    * */
    private String constructURL() {

        return getAuthorizationURL() + "?redirect_uri=" + getRedirectURI() +
                "&response_type=" + getResponseType() + "&client_id=" +
                getClientId() + "&viewtype=" + getViewType() + "&deviceid=";
    }


    private void init(Context context) {
        View rootView = inflate(context, R.layout.layout_fragment, this);
        content = (RelativeLayout) rootView.findViewById(R.id.content);
        webview_ = getInstanceOfWebview(context);
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sp.edit();
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (webview_ != null) {
                // on back from login screen close app
                if (webview_.canGoBack()) {
                    webview_.goBack();

                } else {
                    ((Activity) getContext()).finish();
                }
            }
            return true;
        }
        return super.dispatchKeyEventPreIme(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webview_ != null) {
                // on back from login screen close app
                if (webview_.canGoBack()) {
                    webview_.goBack();

                } else {
                    ((Activity) getContext()).finish();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
    * User has to send their file mainteained
    * p
    *
    * */
    public void setURLFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
            propertiesXML = obtenerDocumentDeByte(outputStream.toByteArray());
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//resources/item[@string]");
            NodeList nl = (NodeList) expr.evaluate(propertiesXML, XPathConstants.NODESET);
            NodeList nodeList = propertiesXML.getElementsByTagName("item");
            for (int x = 0, size = nodeList.getLength(); x < size; x++) {
                if (nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue().
                        equalsIgnoreCase(getContext().getString(R.string.RedirectURI))) {
                    redirectURI = nodeList.item(x).getTextContent().trim();
                } /*else if (nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue().
                        equalsIgnoreCase(getContext().getString(R.string.ResponseType))) {
                    responseType = nodeList.item(x).getTextContent().trim();
                }*/ else if ((nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue().
                        equalsIgnoreCase(getContext().getString(R.string.ClientId)))) {
                    clientId = nodeList.item(x).getTextContent().trim();
                } else if ((nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue().
                        equalsIgnoreCase(getContext().getString(R.string.ViewType)))) {
                    viewType = nodeList.item(x).getTextContent().trim();
                } else if ((nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue().
                        equalsIgnoreCase(getContext().getString(R.string.ClientSecret)))) {
                    clientSecret = nodeList.item(x).getTextContent().trim();
                } else if ((nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue().
                        equalsIgnoreCase(getContext().getString(R.string.TokenURL)))) {
                    tokenURL = nodeList.item(x).getTextContent().trim();
                } /*else if ((nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue().
                        equalsIgnoreCase(getContext().getString(R.string.GrantType)))) {
                    grantType = nodeList.item(x).getTextContent().trim();
                }*/ else if ((nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue().
                        equalsIgnoreCase(getContext().getString(R.string.UserIdURL)))) {
                    userIdURL = nodeList.item(x).getTextContent().trim();
                } else if ((nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue().
                        equalsIgnoreCase(getContext().getString(R.string.LogoutURL)))) {
                    logoutURL = nodeList.item(x).getTextContent().trim();
                } else if ((nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue().
                        equalsIgnoreCase(getContext().getString(R.string.AuthorizationURL)))) {
                    authorizationURL = nodeList.item(x).getTextContent().trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Document obtenerDocumentDeByte(byte[] documentoXml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new ByteArrayInputStream(documentoXml));
    }

    /*
        *
        * Custom Client to Override Pageload functions
        *
        * */
    class BaseWebViewClient extends WebViewClient {
        Icallback_ callback_;
        ArrayList<String> usedCodes = new ArrayList<>();
        String redirectURI, clientSecret, grantType, clientId, tokenURL, CODE, base[], countString[];
        Context context;
        ProgressBar progressBar;
        RelativeLayout layout;
        WebView webview;

        public BaseWebViewClient(ProgressBar progressBar, Context context, Icallback_ callback_, String tokenURL,
                                 String clientId, String redirectURI, String clientSecret, String grantType,
                                 RelativeLayout layout) {
            this.callback_ = callback_;
            this.redirectURI = redirectURI;
            this.clientSecret = clientSecret;
            this.grantType = grantType;
            this.clientId = clientId;
            this.tokenURL = tokenURL;
            this.context = context;
            this.progressBar = progressBar;
            progressBar.setVisibility(View.VISIBLE);
            this.layout = layout;

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            try {
                if (url.contains("error_code")) {
                    //Toast.makeText(loginActivity, R.string.warn_wrong_username_pswd, Toast.LENGTH_LONG).show();
                    return true;
                } else {
                    if (url.contains("code=") && url.contains("calback.html")) {
                        base = url.split("\\?");
                        countString = base[0].split("&");
                        Map<String, String> queryDataItems = new HashMap<>();
                        String[] value_all = url.split("\\?");
                        String[] queryData = value_all[1].split("&");
                        for (int i = 0; i < queryData.length; i++) {
                            if (queryData[i].split("=").length > 1) {
                                queryDataItems.put(queryData[i].split("=")[0], queryData[i].split("=")[1]);
                            }
                        }
                        // login
                        CODE = queryDataItems.get("code");
                        if (CODE != null) {
                            if (!usedCodes.contains(CODE)) {
                                usedCodes.add(CODE);
                                if (CidaasConstants.isInternetAvailable(context)) {
                                    //  Get Access token Form CODE
                                    getAccessToken(tokenURL, CidaasConstants.REST_CONTENT_TYPE_URLENCODED, clientId,
                                            redirectURI, CODE, clientSecret,
                                            grantType);
                                } else {
                                    internetError = getContext().getString(R.string.tryAgain);
                                    getInternetErrorImage(layout, context, internetError);
                                }
                            }

                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            System.out.println("Error:" + failingUrl);
            error_ = true;
            error_description = description;

        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            error_ = true;
            error_description = "HttpError!";
            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            System.out.println("Error:" + error);
            error_ = true;
            error_description = "SSL error!!";
            errorLayout();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (error_ && !error_description.equals("")) {
                errorLayout();
            }
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            view.loadUrl("javascript:$(document).ready(function() {$(document).foundation();}");
        }

        void errorLayout() {
            // layout.removeAllViews();
            getErrorImage(layout, context, error_description);
            System.out.println("Error has occured!");
            progressBar.setVisibility(View.GONE);
        }


        /**
         * Call function get access token
         */
        public void getAccessToken(String tokenURL, String content_type, String client_id,
                                   String redirect_uri, String code, String client_secret, String
                                           grant_type) {
            final String[] access_token = new String[1];
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://your.api.url/")
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            ICidaasAPI service = retrofit.create(ICidaasAPI.class);
            service.getAccessTokenApi1(tokenURL, content_type, client_id,
                    redirect_uri, code, client_secret, grant_type)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginEntity>() {
                        @Override
                        public void onCompleted() {
                            System.out.println("Oncomplete");
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println("Onerror");
                            error_ = true;
                            error_description = e.getMessage();
                        }

                        @Override
                        public void onNext(LoginEntity loginEntity) {
                            access_token[0] = loginEntity.getAccess_token();
                            System.out.println(" access_token" + access_token[0]);
                            ResponseEntity responseEntity = new ResponseEntity();
                            responseEntity.setAccess_token(loginEntity.getAccess_token());
                            responseEntity.setError(null);
                            responseEntity.setSuccess(true);
                            callback_.getResponse(responseEntity);
                            saveLoginDetails(loginEntity);
                            getUserInfoFromAccessToken(access_token[0], context);
                        }
                    });
        }

    }

    private static void saveLoginDetails(LoginEntity loginEntity) {

        String salt = UUID.randomUUID().toString();
        String en = null;
        long timeinmillis = System.currentTimeMillis();
        long time = timeinmillis / 1000;
        time = time + loginEntity.getExpires_in() - 10;
        try {
            en = AESCrypt.encrypt(salt, loginEntity.getAccess_token());
            editor.putString("CidaasAccessToken", en);
            editor.putString("CidaasSalt", salt);
            editor.putString("CidaasRefreshToken", loginEntity.getRefresh_token());
            editor.putLong("CidaasExpiresIn", time);
            editor.commit();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public static UserProfile getUserInfoFromAccessToken(String access_token, final Context context) {
        final UserProfile[] userProfile = {new UserProfile()};
        final ResponseEntity responseEntity = new ResponseEntity();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://your.api.url/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ICidaasAPI service = retrofit.create(ICidaasAPI.class);
        service.getUserDetailsApi(getUserIdURL(), access_token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserProfile>() {
                               @Override
                               public void onCompleted() {
                                   System.out.println("Get user profile completed ");
                               }

                               @Override
                               public void onError(Throwable e) {
                                   //If something went wrong goto login screen
                                   error_ = true;
                                   error_description = e.getMessage();
                                   System.out.println("Get user profile err " + e.getMessage());
                               }

                               @Override
                               public void onNext(UserProfile userInfo) {
                                   userProfile[0] = userInfo;
                                   System.out.println("Get user profile res User id : " + userInfo.getId());
                                   if (sp == null) {
                                       sp = PreferenceManager.getDefaultSharedPreferences(context);
                                   } else if (editor == null) {
                                       editor = sp.edit();
                                   } else {
                                       editor.putString("CidaasUserID", userInfo.getId());
                                       editor.commit();

                                   }
                               }
                           }
                );
        if (userProfile[0] != null)
            return userProfile[0];
        else
            return null;
    }

    public static ResponseEntity getAccessTokenByUserId(String userId) {
        String UserID = sp.getString("UserID", "");
        Long ExpiresIn = sp.getLong("ExpiresIn", 0);
        String AccessToken = sp.getString("AccessToken", "");
        String RefreshToken = sp.getString("RefreshToken", "");
        String Salt = sp.getString("Salt", "");
        long timeinmillis = System.currentTimeMillis();
        long time = timeinmillis / 1000;
        System.out.println("Current Time: " + time + "Expires in: " + ExpiresIn);
        ResponseEntity responseEntity = new ResponseEntity();
        if (UserID != "" && UserID.equals(userId)) {

            if (ExpiresIn > time) {
                try {
                    String de = AESCrypt.decrypt(Salt, AccessToken);
                    responseEntity.setAccess_token(de);
                    responseEntity.setError("Success");
                    responseEntity.setSuccess(true);

                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }

            } else {

                responseEntity = getAccessTokenByRefreshToken(RefreshToken);
            }
        } else {
            responseEntity.setAccess_token(null);
            responseEntity.setError("Invalid UserID!!");
            responseEntity.setSuccess(false);
        }
        return responseEntity;
    }

    private static ResponseEntity getAccessTokenByRefreshToken(String RefreshToken) {
        final ResponseEntity responseEntity = new ResponseEntity();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://your.api.url/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ICidaasAPI service = retrofit.create(ICidaasAPI.class);
        service.getAccessTokenByRefreshToken(tokenURL, CidaasConstants.REST_CONTENT_TYPE_URLENCODED, clientId,
                redirectURI, RefreshToken, clientSecret,
                "refresh_token").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginEntity>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Oncompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        error_ = true;
                        error_description = e.getMessage();
                        System.out.println("onError");
                        responseEntity.setError(e.getMessage());
                        responseEntity.setSuccess(false);
                        responseEntity.setAccess_token(null);
                    }

                    @Override
                    public void onNext(LoginEntity loginEntity) {
                        System.out.println("onNext");
                        saveLoginDetails(loginEntity);
                        responseEntity.setError(null);
                        responseEntity.setSuccess(true);
                        responseEntity.setAccess_token(loginEntity.getAccess_token());
                    }
                });
        return responseEntity;
    }

    public static CidaasSDK getCidaasSDKInst(Context context) {
        if (cidaasHelper == null) {
            cidaasHelper = new CidaasSDK(context);
        }
        return cidaasHelper;
    }

    public static void logout(String UserId, Context context) {
        ResponseEntity responseEntity = getAccessTokenByUserId(UserId);
        if (CidaasConstants.isInternetAvailable(context)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://your.api.url/")
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            ICidaasAPI service = retrofit.create(ICidaasAPI.class);
            service.logoutUser(getLogoutURL(), responseEntity.getAccess_token(), responseEntity.getAccess_token())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Void>() {

                        @Override
                        public void onCompleted() {
                            System.out.println("Log out Oncompleted");
                        }

                        @Override
                        public void onError(Throwable e) {
                            error_ = true;
                            error_description = e.getMessage();
                            System.out.println("Log out onError");

                        }

                        @Override
                        public void onNext(Void aVoid) {
                            System.out.println("Log out onNext");
                            editor.clear();
                            editor.commit();
                        }
                    });
        } else {
            editor.clear();
            editor.commit();
        }

    }
}




