/**
 * Created by Bintang on 2/26/2016.
 * This Class purposing to get new token (refresh)
 */

package bintang.id.listener;

import android.content.Intent;
import com.google.android.gms.iid.InstanceIDListenerService;
import bintang.id.pushnotificationexercise.RegistrationIntentService;

public class InstanceIDListener extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
