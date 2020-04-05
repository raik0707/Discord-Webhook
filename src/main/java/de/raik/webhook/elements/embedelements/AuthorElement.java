package de.raik.webhook.elements.embedelements;

import com.google.gson.JsonObject;

/**
 * Author element of embed
 *
 * @author Raik
 * @version 1.0
 */
public class AuthorElement implements Element {

    /**
     * The author's name
     */
    private String name;

    /**
     * The redirect url
     * of the author
     */
    private String url;

    /**
     * The image of the author
     */
    private String iconURL;

    /**
     * Set the author's icon
     *
     * @param url The url of the icon
     * @return Itself to use it functional
     */
    public AuthorElement icon(String url) {
        this.iconURL = url;
        return this;
    }

    /**
     * Set the redirect url
     * of the author
     *
     * @param redirectURL The url the author will redirect to
     * @return Itself to use it functional
     */
    public AuthorElement redirect(String redirectURL) {
        this.url = redirectURL;
        return this;
    }

    /**
     * Creating element
     * and setting the name of the author
     *
     * @param name The author's name
     */
    public AuthorElement(String name) {
        this.name = name;
    }

    /**
     * Converts element to jsonobject
     *
     * @return The exported Json object
     */
    @Override
    public JsonObject exportToJson() {
        JsonObject authorObject = new JsonObject();

        //Setting name
        authorObject.addProperty("name", this.name);

        //Setting icon if it is not null
        if (this.iconURL != null)
            authorObject.addProperty("icon_url", this.iconURL);

        //Setting redirect url if it is not null
        if (this.url != null)
            authorObject.addProperty("url", this.url);

        return authorObject;
    }
}
