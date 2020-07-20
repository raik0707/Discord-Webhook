package de.raik.webhook;

import de.raik.webhook.elements.AllowedMentions;
import de.raik.webhook.elements.Embed;
import de.raik.webhook.elements.embedelements.AuthorElement;
import de.raik.webhook.elements.embedelements.FieldElement;
import de.raik.webhook.elements.embedelements.FooterElement;
import de.raik.webhook.elements.embedelements.ImageElement;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class WebhookTest {

    /*
     * Webbhook URL
     * put your url in here.
     */
    private static final String WEBHOOK_URL = "";

    @Test
    public void testWebhook() throws IOException {
        Webhook testWebhook = new WebhookBuilder(WEBHOOK_URL)
                .username("First JUnit Test")
                .tts()
                .avatar("https://www.labymod.net/page/tpl/assets/images/logo_web.png")
                .content("Jakob lol")
                .addEmbed(new Embed()
                        .color("#32a852")
                        .author(new AuthorElement("Scrummi")
                                .icon("https://pbs.twimg.com/profile_images/825375357117353988/IHVk7TXJ_400x400.jpg")
                                .redirect("https://playgalaxy.net"))
                        .description("Description")
                        .footer(new FooterElement("footer")
                                .icon("https://pbs.twimg.com/profile_images/637970381022855168/0YLpCvRY_400x400.jpg"))
                        .image(new ImageElement("https://crafatar.com/avatars/199b1f41869e4769b4dc8c21487b112c?size=75&overlay"))
                        .fields(new FieldElement("One", "One").inline(), new FieldElement("Two", "Two"))
                        .redirect("https://google.com")
                        .title("Title")
                        .thumbnail(new ImageElement("https://crafatar.com/avatars/199b1f41869e4769b4dc8c21487b112c?size=75&overlay"))
                        .timestamp(LocalDateTime.now()))
                .allowedMentions(new AllowedMentions()
                        .parseEveryone()
                        .parseRoles()
                        .parseUsers()).build();
        assertEquals(204, testWebhook.execute().getResponseCode());
    }

}
