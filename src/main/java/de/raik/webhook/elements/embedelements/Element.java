package de.raik.webhook.elements.embedelements;

import com.google.gson.JsonObject;

/**
 * Interface
 * which represents all
 * embed objects
 *
 * @author Raik
 * @version 1.0
 */
public interface Element {

    /**
     * Converts element to jsonobject
     *
     * @return The exported Json object
     */
    JsonObject exportToJson();

}
