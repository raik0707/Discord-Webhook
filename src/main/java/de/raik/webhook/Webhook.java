package de.raik.webhook;

import com.google.gson.JsonObject;

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
}
