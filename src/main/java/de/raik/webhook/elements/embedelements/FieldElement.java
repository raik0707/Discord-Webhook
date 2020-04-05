package de.raik.webhook.elements.embedelements;

import com.google.gson.JsonObject;

public class FieldElement {

    /**
     * The name of the field
     */
    private String name;

    /**
     * The value of the field
     */
    private String value;

    /**
     * If the field is inline
     */
    private boolean inline = false;

    /**
     * Creating field element
     * with name and value
     *
     * @param name The name of field
     * @param value The value of the field
     */
    public FieldElement(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Enables inline
     *
     * @return Itself for functional use
     */
    public FieldElement inline() {
        this.inline = true;
        return this;
    }

    public JsonObject exportToJson() {
        JsonObject fieldObject = new JsonObject();

        //Setting name
        fieldObject.addProperty("name", this.name);

        //Setting value
        fieldObject.addProperty("value", this.value);

        //Setting inline
        fieldObject.addProperty("inline", this.inline);

        return fieldObject;
    }
}
