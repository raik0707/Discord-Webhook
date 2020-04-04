package de.raik.webhook.elements.embedelements;

import com.google.gson.JsonObject;

/**
 *  Footer element of webhook
 *
 * @author Raik
 * @version 1.0
 */
public class FooterElement implements Element {

    /**
     * The text of the footer
     */
    private String text;

    /**
     * The icon url of the the footer
     */
    private String iconURL;

    /**
     * Set the icon of the footer
     *
     * @param url The url of the icon
     * @return Itself to use it functional
     */
    public FooterElement icon(String url) {
        this.iconURL = url;
        return this;
    }

    /**
     * Creating element
     * and setting the text
     *
     * @param text The text which will be set
     */
    public FooterElement(String text) {
        this.text = text;
    }

    /**
     * Converts element to jsonobject
     *
     * @return The exported Json object
     */
    @Override
    public JsonObject exportToJson() {
        JsonObject footerObject = new JsonObject();

        //Setting text
        footerObject.addProperty("text", this.text);

        //Setting icon if it is not null
        if (this.iconURL != null)
            footerObject.addProperty("icon_url", this.iconURL);

        return footerObject;
    }
}
