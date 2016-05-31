package bintang.id.pushnotificationexercise;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.test.ApplicationTestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public static final String API_KEY = "AIzaSyCXM7I0s_lxKjQN_3xFtFfETX0YBqr5pj4";
    public static final String PROPERTY_REG_ID = "registration_id";
    public void testSendNotify() throws IOException, JSONException {
        JSONObject jGcmData = new JSONObject();
        JSONObject jData = new JSONObject();
        jData.put("message", "Hello Worlds!!");
        // Where to send GCM message.
        final SharedPreferences prefs = getGCMPreferences();
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");

        jGcmData.put("to", registrationId);
        // What to send in GCM message.
        jGcmData.put("data", jData);

        // Create connection to send GCM Message request.
        URL url = new URL("https://android.googleapis.com/gcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "key=" + API_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        // Send GCM message content.
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(jGcmData.toString().getBytes());

        // Read GCM response.
      assertEquals(conn.getResponseCode(), HttpURLConnection.HTTP_OK);

    }

    private SharedPreferences getGCMPreferences() {

        return getContext().getSharedPreferences(RegistrationIntentService.class.getSimpleName(), Context.MODE_PRIVATE);
    }
}