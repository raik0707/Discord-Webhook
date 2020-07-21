package de.raik.webhook;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Webhook class
 * presents executable webhook
 *
 * @author Raik
 * @version 1.0
 */
public class Webhook {

    /**
     * JsonObject which
     * represents the request payload
     */
    private final JsonObject webhookJson;

    /**
     * URL where to request
     * will be sent to
     */
    private final String url;

    /**
     * Constructor which is used by WebHookBuilder
     * to create the webhook
     * @see WebhookBuilder
     * It set the attribtues
     *
     * @param url URL where to object url will be set to
     * @param json JsonObject which represents the webhook payload
     */
    protected Webhook(String url, JsonObject json) {
        this.url = url;
        this.webhookJson = json;
    }

    /**
     * Executes the webhook
     * with a post request
     *
     * @return The httpConnection of the request to use the result of the request
     */
    public HttpURLConnection execute() {
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) new URL(this.url).openConnection();
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");

            OutputStream outputStream = httpConnection.getOutputStream();
            byte[] payloadBytes = this.webhookJson.toString().getBytes(StandardCharsets.UTF_8);
            outputStream.write(payloadBytes, 0, payloadBytes.length);
            outputStream.flush();
            outputStream.close();

            httpConnection.getInputStream();

            return httpConnection;

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
