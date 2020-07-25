package de.raik.webhook;

import de.raik.webhook.elements.Embed;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class WebhookBuilderTest {

    /**
     * Try if the builder returns null on build if url is empty
     */
    @Test
    public void testEmptyUrl() {
        WebhookBuilder builder = new WebhookBuilder(null);

        //Setting content to prevent other null possibility
        builder.content("Content");

        assertNull("Builds with empty url, as well", builder.build());
    }

    /**
     * Try it the builder returns null on build if content is empty
     */
    @Test
    public void testEmptyContent() {
        //Set non null url to prevent url not buildable
        WebhookBuilder builder = new WebhookBuilder("");

        assertNull("Builds with empty content", builder.build());
    }

    /**
     * Check if it builds with set content and url
     */
    @Test
    public void testWithContent() {
        //Set non null url to prevent url not buildable
        WebhookBuilder builder = new WebhookBuilder("");

        builder.content("Content");

        assertNotNull("Doesn't build with content and url", builder);
    }

    /**
     * Check if it builds with set content and embed
     */
    @Test
    public void testWithEmbed() {
        //Set non null url to prevent url not buildable
        WebhookBuilder builder = new WebhookBuilder("");

        builder.addEmbed(new Embed().description("Testing embed"));

        assertNotNull("Doesn't build with content and embed", builder);
    }
}
