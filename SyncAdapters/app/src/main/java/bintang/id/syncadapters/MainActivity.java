package bintang.id.syncadapters;

/**
 * Created by bintang on 3/7/2016.
 * Purpose : To make a sync between client(apps) and server.
 * Basically, an app has automatically sync with the server since they installed,
 * using command ContentResolver.setIsSyncable. If you want to sync after network connected,
 * using should use ContentResolver.setSyncAutomatically. If you want to trigger sync
 * from UI by user choice, you should use ContentResolver.requestSync.
 * If we want to make sync each interval, we should use ContentResolver.addPeriodicSync.
 */

import java.util.ArrayList;
import java.util.List;

import bintang.id.listadapter.Item;
import bintang.id.listadapter.ListAdapter;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    private String TAG = this.getClass().getSimpleName();
    private AccountManager mAccountManager;
    private List<Item> list = null;
    private ListView listView;
    private ListAdapter listAdaptor;
    public static final String DEMO_ACCOUNT_NAME = "luisginan@gmail.com";//"Demo Account";
    public static final String DEMO_ACCOUNT_PASSWORD = "123456";//"Demo123";
    private Button button1, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        mAccountManager = AccountManager.get(this);
        initComponent();
        //createDemoAccount();
    }

    private void initComponent(){
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(syncStaredReceiver, new IntentFilter(SyncAdapter.SYNC_STARTED));
        registerReceiver(syncFinishedReceiver, new IntentFilter(SyncAdapter.SYNC_FINISHED));
        showAccounts();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(syncStaredReceiver);
        unregisterReceiver(syncFinishedReceiver);
    }

    private BroadcastReceiver syncFinishedReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Sync finished!");
            Toast.makeText(getApplicationContext(), "Sync Finished...", Toast.LENGTH_SHORT).show();
        }
    };

    private BroadcastReceiver syncStaredReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Sync started!");
            Toast.makeText(getApplicationContext(), "Sync started...", Toast.LENGTH_SHORT).show();
        }
    };

    private void startSyncOnDemand() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        Account account = new Account(DEMO_ACCOUNT_NAME, getString(R.string.auth_type));

        ContentResolver.setIsSyncable(account, getString(R.string.content_authority), 1);
        ContentResolver.setSyncAutomatically(account, getString(R.string.content_authority), true);
        ContentResolver.requestSync(account, getString(R.string.content_authority), bundle);
    }


    private void scheduleSync() {
        Account account = new Account(DEMO_ACCOUNT_NAME, getString(R.string.auth_type));
        ContentResolver.addPeriodicSync(account, getString(R.string.content_authority), new Bundle(), 10);
    }

    private void showMessage(final String msg) {
        if (TextUtils.isEmpty(msg))
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<Item> getData() {
        ArrayList<Item> accountsList = new ArrayList<Item>();

        // Getting all registered Our Application Accounts;
        try {
            Account[] accounts = AccountManager.get(this).getAccountsByType(getString(R.string.auth_type));
            for (Account account : accounts) {
                Item item = new Item(account.type, account.name);
                accountsList.add(item);
            }
        } catch (Exception e) {
            Log.i(TAG, "Exception:" + e);
        }

        // For all registered accounts;
		/*
		 * try { Account[] accounts = AccountManager.get(this).getAccounts();
		 * for (Account account : accounts) { Item item = new Item(
		 * account.type, account.name); accountsList.add(item); } } catch
		 * (Exception e) { Log.i("Exception", "Exception:" + e); }
		 */
        return accountsList;
    }

    void  createDemoAccount() {
        Account account = new Account(DEMO_ACCOUNT_NAME, getString(R.string.auth_type));
        boolean accountCreated = mAccountManager.addAccountExplicitly(account, DEMO_ACCOUNT_PASSWORD, null);
        if (accountCreated) {
            showMessage("Account Created");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            Intent createIntent = new Intent(MainActivity.this, AuthenticatorActivity.class);
            startActivity(createIntent);
        }
        if (v.getId() == R.id.button3) {
            startSyncOnDemand();
        }
        if (v.getId() == R.id.button4) {
            scheduleSync();
        }
    }

    private void showAccounts() {
        if (null != list && list.size() > 0)
            list.clear();
        else
            list = new ArrayList<Item>();

        list.addAll(getData());
        if (list.size() > 0) {
            if (null == listAdaptor) {
                listView = (ListView) findViewById(R.id.listView1);
                listAdaptor = new ListAdapter(MainActivity.this, R.layout.row_layout, list);
                listView.setAdapter(listAdaptor);
            } else {
                listAdaptor.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Accounts Added.", Toast.LENGTH_SHORT).show();
        }
    }
}
