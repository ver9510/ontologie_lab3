package ontologie_lab3.utils.jsonquery;

import ontologie_lab3.utils.TrustedHttpClientFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class JSON {
    private static final Logger logger = LogManager.getLogger(MuseumQueries.class.getName());

    public static String readJSON(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = null;
        try {
            httpClient = TrustedHttpClientFactory.createHttpClient_AcceptsUntrustedCerts();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        HttpGet httpGet = new HttpGet(URL);

        try {

            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200) {

                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                inputStream.close();

            } else {
                logger.info("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            logger.info("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }
}
