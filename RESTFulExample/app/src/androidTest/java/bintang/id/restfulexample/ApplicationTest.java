package bintang.id.restfulexample;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.apache.http.HttpResponse;

import java.util.Objects;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    public void testGet(){
        try{
            String passValue = "http://homecamportal.cloudapp.net/Api/User/Signin?email=luisginan@gmail.com&password=123456";
            RestClient client = new RestClient();
            HttpResponse result = client.get(passValue);

            if (result.getStatusLine().getStatusCode() == HTTP_OK) {
                String content = RestClient.getContent(result);
                if (!Objects.equals(content, "")) {
                    return;
                }
                throw new Exception("Http response Don't have content");

            }

            if (result.getStatusLine().getStatusCode() == HTTP_NOT_FOUND)
                return;

            throw new Exception(result.getStatusLine().toString());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void testPost(){
        try{
            String url = "http://homecamportal.cloudapp.net/api/Camera/InsertCamera";
            RestClient client = new RestClient();

            String json = "{\n" +
                    "  \"TABLE_NAME\": \"Cameras\",\n" +
                    "  \"name\": \"Test\",\n" +
                    "  \"userRK\": \"123456\",\n" +
                    "  \"UID\": \"55555\",\n" +
                    "  \"created\": \"2016-02-05T07:36:36.6834858+00:00\",\n" +
                    "  \"updated\": \"2016-02-05T07:36:36.6834858+00:00\",\n" +
                    "  \"PartitionKey\": \"sample string 6\",\n" +
                    "  \"RowKey\": \"sample string 7\",\n" +
                    "  \"Timestamp\": \"2016-02-05T07:36:36.6834858+00:00\",\n" +
                    "  \"ETag\": \"sample string 9\"\n" +
                    "}";

            HttpResponse result = client.post(url, "Basic bHVpc2dpbmFuQGdtYWlsLmNvbToxMjM0NTY=", json);

            if (result.getStatusLine().getStatusCode() == HTTP_OK)
                return;

            if (result.getStatusLine().getStatusCode() == HTTP_NOT_FOUND)
                return;

            throw new Exception(result.getStatusLine().toString());

        }catch (Exception ex){
            assertFalse(ex.getMessage(),true);
        }
    }

    public void testPut(){
        try{
            String url = "http://homecamportal.cloudapp.net/api/User/UpdateProfile";
            RestClient client = new RestClient();

            String auth = "Basic bHVpc2dpbmFuQGdtYWlsLmNvbToxMjM0NTY=";

            String json = "{\n" +
                    "  \"TABLE_NAME\": \"Users\",\n" +
                    "  \"fullName\": \"sample string 1\",\n" +
                    "  \"passwordHash\": \"sample string 2\",\n" +
                    "  \"email\": \"sample string 3\",\n" +
                    "  \"city\": \"sample string 4\",\n" +
                    "  \"country\": \"sample string 5\",\n" +
                    "  \"active\": true,\n" +
                    "  \"isMovePrimaryDevice\": true,\n" +
                    "  \"isLostDevice\": true,\n" +
                    "  \"role\": 9,\n" +
                    "  \"uniqueAppCode\": \"sample string 10\",\n" +
                    "  \"created\": \"2016-02-05T10:41:33.4691991+00:00\",\n" +
                    "  \"updated\": \"2016-02-05T10:41:33.4691991+00:00\",\n" +
                    "  \"PartitionKey\": \"sample string 13\",\n" +
                    "  \"RowKey\": \"sample string 14\",\n" +
                    "  \"Timestamp\": \"2016-02-05T10:41:33.4691991+00:00\",\n" +
                    "  \"ETag\": \"sample string 16\"\n" +
                    "}";


            HttpResponse result = client.put(url, auth, json);

            if (result.getStatusLine().getStatusCode() == HTTP_OK)
                return;

            if (result.getStatusLine().getStatusCode() == HTTP_NOT_FOUND)
                return;

            throw new Exception(result.getStatusLine().toString());

        }catch (Exception ex){
            assertFalse(true);
        }
    }

    public void testDelete(){
        try {
            String url = "http://homecamportal.cloudapp.net/api/Camera/DeleteCamera?cameraRK=12345";
            RestClient client = new RestClient();
            String auth = "Basic bHVpc2dpbmFuQGdtYWlsLmNvbToxMjM0NTY=";
            HttpResponse httpResponse = client.delete(url, auth);

            if (httpResponse.getStatusLine().getStatusCode() == HTTP_OK)
                return;

            if (httpResponse.getStatusLine().getStatusCode() == HTTP_NOT_FOUND)
                return;

            throw new Exception(httpResponse.getStatusLine().toString());

        } catch (Exception ex) {
            assertFalse(ex.toString(),true);
        }
    }
}