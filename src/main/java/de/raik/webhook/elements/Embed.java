package de.raik.webhook.elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.raik.webhook.elements.embedelements.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
     * The title of the embed
     */
    private String title;

    /**
     * The embed description
     */
    private String description;

    /**
     * The embed url
     */
    private String url;

    /**
     * The embeds color
     */
    private int color = -1;

    /**
     * The webhook timestamp
     */
    private String timestamp;

    /**
     * List of element which are set in
     * own objects
     */
    private final HashMap<String, Element> objectEmbedElements = new HashMap<>();

    /**
     * List of all embed fields
     */
    private final ArrayList<FieldElement> embedFields = new ArrayList<>();

    /**
     * Set the title of the embed
     *
     * @param title The embed title
     * @return Itself for functional use
     */
    public Embed title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Set the embed description
     *
     * @param description The description it will be set to
     * @return Itself for functional use
     */
    public Embed description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Set the redirect url
     *
     * @param url The url of the redirect
     * @return It self for functional use
     */
    public Embed redirect(String url) {
        this.url = url;
        return this;
    }

    /**
     * Setting color of embed
     *
     * @param hexColor The color as hexcode
     * @return Itself for functional use
     * @throws NumberFormatException If the hex code is not valid
     */
    public Embed color(String hexColor) throws NumberFormatException {
        /*
         * If hexColor starts with #
         * remove it
         */
        while (hexColor.startsWith("#"))
            hexColor = hexColor.substring(1);

        //Parsing decimal from hexColor code
        this.color = Integer.parseInt(hexColor, 16);

        return this;
    }

    /**
     * Set the timestamp
     *
     * @param dateTime Set the datetime as
     *                 LocalDateTime to prevent
     *                 formatting errors
     * @see LocalDateTime
     * @return Itself for functional use
     */
    public Embed timestamp(LocalDateTime dateTime) {
        this.timestamp = dateTime.toString();
        return this;
    }

    /**
     * Set the embed author
     *
     * @param authorElement The author element it will be set to
     * @see AuthorElement
     * @return Itself for functional use
     */
    public Embed author(AuthorElement authorElement) {
         /*
          * Remove if it is already in
          */
         this.objectEmbedElements.remove("author");
         this.objectEmbedElements.put("author", authorElement);

         return this;
    }

    /**
     * Set the embed footer
     *
     * @param footerElement The footer element it will be set to
     * @see FooterElement
     * @return Itself for functional use
     */
    public Embed footer(FooterElement footerElement) {
        //Remove if it is already in
        this.objectEmbedElements.remove("footer");
        this.objectEmbedElements.put("footer", footerElement);

        return this;
    }

    /**
     * Set the image
     *
     * @param imageElement The image element it will be set to
     * @see ImageElement
     * @return Itself for functional use
     */
    public Embed image(ImageElement imageElement) {
        //Remove if it is already in
        this.objectEmbedElements.remove("image");
        this.objectEmbedElements.put("image", imageElement);

        return this;
    }

    /**
     * Set the image
     *
     * @param thumbnailElement The image element it will be set to
     *                         using ImageElement because images and
     *                         thumbnails work nearly equals
     * @see ImageElement
     * @return Itself for functional use
     */
    public Embed thumbnail(ImageElement thumbnailElement) {
        //Remove if it is already in
        this.objectEmbedElements.remove("thumbnail");
        this.objectEmbedElements.put("thumbnail", thumbnailElement);

        return this;
    }

    /**
     * Add new embed field to the embed
     *
     * @param fields The embeds which will be added
     * @return Itself for functional use
     */
    public Embed fields(FieldElement... fields) {
        this.embedFields.addAll(Arrays.asList(fields));
        return this;
    }

    /**
     * Converts embed into json
     *
     * @return The JsonObject it is converted to
     */
    public JsonObject exportJson() {
        JsonObject embedObject = new JsonObject();

        //Setting basic attributes
        if (this.title != null)
            embedObject.addProperty("title", this.title);
        if (this.description != null)
            embedObject.addProperty("description", this.description);
        if (this.url != null)
            embedObject.addProperty("url", this.url);
        if (this.timestamp != null)
            embedObject.addProperty("timestamp", this.timestamp);
        if (this.color  != -1)
            embedObject.addProperty("color", this.color);

        //Adding the object based properties
        this.objectEmbedElements.forEach((property, element) -> embedObject.add(property, element.exportToJson()));

        //Adding fields
        JsonArray fields = new JsonArray();
        this.embedFields.forEach(fieldElement -> fields.add(fieldElement.exportToJson()));

        embedObject.add("fields", fields);

        return embedObject;
    }
}
