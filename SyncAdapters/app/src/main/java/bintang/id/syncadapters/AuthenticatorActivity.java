package bintang.id.syncadapters;

/**
 * Created by bintang on 3/7/2016.
 * To Handle if there's no account listed or user want to add another account
 */


import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AuthenticatorActivity extends AccountAuthenticatorActivity implements OnClickListener {

    public final static String ARG_ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static String ARG_AUTH_TYPE = "AUTH_TYPE";
    public final static String ARG_ACCOUNT_NAME = "ACCOUNT_NAME";
    public final static String PARAM_USER_PASS = "USER_PASS";

    private final String TAG = this.getClass().getSimpleName();

    private AccountManager mAccountManager;
    private String mAuthTokenType;
    String authToken;
    String password;

    String accountName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        mAccountManager = AccountManager.get(getBaseContext());

        // If this is a first time adding, then this will be null
        accountName = getIntent().getStringExtra(ARG_ACCOUNT_NAME);
        mAuthTokenType = getIntent().getStringExtra(ARG_AUTH_TYPE);

        if (mAuthTokenType == null)
            mAuthTokenType = getString(R.string.auth_type);

        System.out.println(mAuthTokenType + ", accountName : " + accountName);

        ((Button) findViewById(R.id.submit)).setOnClickListener(this);
    }

    void userSignIn() {

        // You should probably call your server with user credentials and get
        // the authentication token here.
        // For demo, I have hard-coded it.
        authToken = "123456788";
        password = "12345";

        accountName = ((EditText) findViewById(R.id.accountName)).getText().toString().trim();
        password = ((EditText) findViewById(R.id.accountPassword)).getText().toString().trim();

        if (accountName.length() > 0) {
            Bundle data = new Bundle();
            data.putString(AccountManager.KEY_ACCOUNT_NAME, accountName);
            data.putString(AccountManager.KEY_ACCOUNT_TYPE, mAuthTokenType);
            data.putString(AccountManager.KEY_AUTHTOKEN, authToken);
            data.putString(PARAM_USER_PASS, password);

            // Some extra data about the user, demo values
            Bundle userData = new Bundle();
            userData.putString("UserID", "27");
            data.putBundle(AccountManager.KEY_USERDATA, userData);

            // Make it an intent to be passed back to the Android Authenticator
            final Intent res = new Intent();
            res.putExtras(data);

            // Create the new account with Account Name and TYPE
            final Account account = new Account(accountName, mAuthTokenType);

            // Add the account to the Android System
            if (mAccountManager.addAccountExplicitly(account, password, userData)) {
                // worked
                Log.d(TAG, "Account added");
                mAccountManager.setAuthToken(account, mAuthTokenType, authToken);
                setAccountAuthenticatorResult(data);
                setResult(RESULT_OK, res);
                finish();
            } else {
                // guess not
                Log.d(TAG, "Account NOT added");
            }

        }
    }

    @Override
    public void onClick(View v) {
        userSignIn();
    }

}
