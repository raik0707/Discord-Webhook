package de.raik.webhook.elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashSet;

/**
 * Class which represents
 * the Allowed Mentions of
 * a webhook
 *
 * @author Raik
 * @version 1.0
 */
public class AllowedMentions {

    /**
     * Value which represents if @everyone
     * will be passed
     */
    private boolean parseEveryone = false;

    /**
     * Value which represents if roles are pinged
     */
    private boolean parseRoles = false;

    /**
     * Value which represent if users are pinged
     */
    private boolean parseUsers = false;

    /**
     * The users which will be mentioned
     */
    private final HashSet<String> userSnowflakes = new HashSet<>();

    /**
     * The roles which will be mentioned
     */
    private final HashSet<String> roleSnowflakes = new HashSet<>();

    /**
     * Activate mention parsing
     * for @everyone and @here
     *
     * @return Itself to continue modifying
     */
    public AllowedMentions parseEveryone() {
        this.parseEveryone = true;
        return this;
    }

    /**
     * Activate all role pings
     *
     * @return Itself to continue modifying
     */
    public AllowedMentions parseRoles() {
        this.parseRoles = true;

        // Clearing roles snowflakes to prevent invalid configurations
        this.roleSnowflakes.clear();

        return this;
    }

    /**
     * Activate all user pings
     *
     * @return Itself to continue modifying
     */
    public AllowedMentions parseUsers() {
        this.parseUsers = true;

        // Clearing user snowflakes to prevent invalid configurations
        this.userSnowflakes.clear();

        return this;
    }

    /**
     * Add role to the mentioned roles
     *
     * @param roleSnowflake The role snowflake which will be added
     * @return Itself to continue modifying
     */
    public AllowedMentions addRole(String roleSnowflake) {
        /*
         * Stop adding role snowflake if parseRoles is enabled
         * to prevent invalid configurations
         *
         * Stop adding role snowflake if there are already 100
         * entries because it's limited to 100
         * to prevent errors
         */
        if (this.parseRoles || this.roleSnowflakes.size() > 100)
            return this;

        this.roleSnowflakes.add(roleSnowflake);
        return this;
    }

    /**
     * Add user to the mentioned users
     *
     * @param userSnowflake The user snowflake which will be added
     * @return Itself to continue modifying
     */
    public AllowedMentions addUser(String userSnowflake) {
        /*
         * Stop adding user snowflake if parseRoles is enabled
         * to prevent invalid configurations
         *
         * Stop adding user snowflake if there are already 100
         * entries because it's limited to 100
         * to prevent errors
         */
        if (this.parseUsers || this.userSnowflakes.size() > 100)
            return this;

        this.userSnowflakes.add(userSnowflake);
        return this;
    }

    /**
     * Export the allowed mentions object
     * to json to use it in WebhookBuilder
     * @see de.raik.webhook.WebhookBuilder
     *
     * @return The exported json
     */
    public JsonObject exportToJson() {
        //Creating object json
        JsonObject allowedMentionsObject = new JsonObject();

        //Implementing parse field
        if (this.parseUsers || this.parseRoles || this.parseEveryone) {
            JsonArray parseArray = new JsonArray();
            if (this.parseUsers)
                parseArray.add("users");
            if (this.parseRoles)
                parseArray.add("roles");
            if (this.parseEveryone)
                parseArray.add("everyone");

            allowedMentionsObject.add("parse", parseArray);
        }

        //Implementing roles field
        if (this.roleSnowflakes.size() > 0) {
            JsonArray rolesJson = new JsonArray();
            this.roleSnowflakes.forEach(rolesJson::add);

            allowedMentionsObject.add("roles", rolesJson);
        }

        //Implementing users field
        if (this.userSnowflakes.size() > 0) {
            JsonArray usersJson = new JsonArray();
            this.userSnowflakes.forEach(usersJson::add);

            allowedMentionsObject.add("users", usersJson);
        }

        return allowedMentionsObject;
    }
}
