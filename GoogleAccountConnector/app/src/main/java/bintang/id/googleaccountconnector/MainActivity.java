package bintang.id.googleaccountconnector;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements OnClickListener, ConnectionCallbacks, OnConnectionFailedListener {

    private static final String TAG = "RetrieveAccessToken";

    Button signOutButton;
    Button userInfoButton;
    Button getTokenButton;
    Button revokeAccessButton;

    private GoogleApiClient mGoogleApiClient;
    GoogleSignInAccount acctSignInAccount;

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signOutButton       = (Button) findViewById(R.id.sign_out_button);
        userInfoButton      = (Button) findViewById(R.id.show_userinfo_button);
        getTokenButton = (Button) findViewById(R.id.get_tokenData);
        revokeAccessButton  = (Button) findViewById(R.id.revoke_access_button);


        signOutButton.setOnClickListener(this);
        userInfoButton.setOnClickListener(this);
        getTokenButton.setOnClickListener(this);
        revokeAccessButton.setOnClickListener(this);
        findViewById(R.id.user_options_button).setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);


        mGoogleApiClient = buildGoogleAPIClient();
        mGoogleApiClient.registerConnectionCallbacks(this);
        mGoogleApiClient.registerConnectionFailedListener(this);
        mGoogleApiClient.registerConnectionFailedListener(this);
    }

    private GoogleApiClient buildGoogleAPIClient() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.server_client_id))
                .build();

        return  new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 9001) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        showToast("Signin : " + result.getStatus());
        if (result.isSuccess()) {
            acctSignInAccount = result.getSignInAccount();
        }
    }

    //region button click
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.sign_in_button:
                signIn_Click();
                break;
            case R.id.sign_out_button:
                signOut_Click();
                break;
            case R.id.show_userinfo_button:
                showUserInfo_Click();
                break;
            case R.id.get_tokenData:
                showToken_Click();
                break;
            case R.id.revoke_access_button:
                revokeAccess_Click();
                break;
        }
    }

    private void signIn_Click() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, 9001);
    }

    private void showToken_Click() {
        showToast("Token : " + acctSignInAccount.getIdToken());
    }

    private void showUserInfo_Click() {
        showToast(("Display name : " + acctSignInAccount.getDisplayName()) + System.getProperty("line.separator") +
                "Email : " + acctSignInAccount.getEmail() + System.getProperty("line.separator") +
                "ID : " + acctSignInAccount.getId() + System.getProperty("line.separator") +
                "Auth Codec : " + acctSignInAccount.getServerAuthCode() + System.getProperty("line.separator") +
                "Photo url : " + acctSignInAccount.getPhotoUrl() + System.getProperty("line.separator"));
    }

    private void signOut_Click() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        acctSignInAccount = null;
                        showToast("Sign out : " + status.toString());
                    }
                });
    }

    private void revokeAccess_Click() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        showToast("Revoke : " + status.toString());
                    }
                });
    }

    //endregion

    //region Listener google client

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        showToast("Google client connected");
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        showToast("Google client connected");
    }

    @Override
    public void onConnectionSuspended(int cause) {
        showToast("Google client suspended");
    }

    //endregion







}
