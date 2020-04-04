package de.raik.webhook.elements;

import com.google.gson.JsonObject;
import de.raik.webhook.elements.embedelements.Element;

import java.util.HashMap;

/**
 * Class which represents
 * the webhook object
 *
 * @author Raik
 * @version 1.0
 */
public class Embed {

    /**
     * The title of the webhook
     */
    private String title;

    /**
     * The webhook description
     */
    private String description;

    /**
     * The webhook url
     */
    private String url;

    /**
     * The webhook color
     */
    private int color;

    /**
     * The webhook timestamp
     */
    private String timestamp;

    /**
     * List of element which are set in
     * own objects
     */
    private HashMap<String, Element> objectWebhookElements = new HashMap<>();


    public JsonObject exportJson() {
        return new JsonObject();
    }
}
