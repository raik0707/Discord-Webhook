package de.raik.webhook;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.raik.webhook.embeds.Embed;

import java.util.ArrayList;

/**
 * Class that easily creates
 * a webhook object
 *
 * @author Raik
 * @version 1.0
 */
public class WebhookBuilder {

    /**
     * The url the message will be posted to
     */
    private final String url;

    /**
     * The message content of the webhook
     */
    private String content;

    /**
     * The username of the webhook
     */
    private String username;

    /**
     * The url to the avatar the
     * webhook will have
     */
    private String avatarURL;

    /**
     * Is the message is a
     * tts message
     */
    private boolean tts = false;

    /**
     * The embeds the message
     * will contain
     */
    private ArrayList<Embed> embeds = new ArrayList<>();

    /**
     * Constructor which creates the
     * webhook builder
     *
     * @param url The url the webhook will be sent to
     */
    public WebhookBuilder(String url) {
        this.url = url;
    }

    /**
     * Set the message content
     *
     * @param content The content the message content will be set to
     * @return Itself to continuing modifying
     */
    public WebhookBuilder content(String content) {
        this.content = content;
        return this;
    }

    /**
     * Set the username of the webhook
     *
     * @param username The username of the webhook
     * @return Itself to continuing modifying
     */
    public WebhookBuilder username(String username) {
        this.username = username;
        return this;
    }

    /**
     * Set the avatar url of the webhook
     *
     * @param avatarURL The url of the webhook
     * @return Itself to continuing modifying
     */
    public WebhookBuilder avatar(String avatarURL) {
        this.avatarURL = avatarURL;
        return this;
    }

    /**
     * Enable tts
     *
     * @return Itself to continuing modifying
     */
    public WebhookBuilder tts() {
        this.tts = true;
        return this;
    }

    /**
     * Adding an embed to the message
     *
     * @param embed The embed which will be added
     * @return Itself to continuing modifying
     */
    public WebhookBuilder addEmbed(Embed embed) {
        /*
         * Only adding embed if the count is smaller then 10
         * Because they are limited to 10
         */
        if (this.embeds.size() < 10)
            this.embeds.add(embed);


        return this;
    }

    /**
     * Building the webhook
     * which was set up in this builder
     * Using JsonObject to create the payload
     * @see com.google.gson.JsonObject
     * @see Webhook
     *
     * @return The Webhook which was created
     */
    public Webhook build() {
        // Creating json payload
        JsonObject payload = new JsonObject();

        if (this.content != null)
            payload.addProperty("content", this.content);
        if (this.username != null)
            payload.addProperty("username", this.username);
        if (this.avatarURL != null)
            payload.addProperty("avatar_url", this.avatarURL);
        payload.addProperty("tts", this.tts);

        JsonArray embedArray = new JsonArray();
        this.embeds.forEach(embed -> embedArray.add(embed.exportJson()));

        payload.add("embeds", embedArray);

        //Returning new webhook
        return new Webhook(this.url, payload);
    }
}
