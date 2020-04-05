package de.raik.webhook.elements.embedelements;

import com.google.gson.JsonObject;

public class ImageElement implements Element {

    /**
     * The url of the image
     */
    private String url;

    /**
     * Creating ImageElement
     * Setting url of picture
     *
     * @param url The url of the picture
     */
    public ImageElement(String url) {
        this.url = url;
    }

    /**
     * Converts element to jsonobject
     *
     * @return The exported Json object
     */
    @Override
    public JsonObject exportToJson() {
        JsonObject imageObject = new JsonObject();

        //Setting url
        imageObject.addProperty("url", this.url);

        return imageObject;
    }
}
