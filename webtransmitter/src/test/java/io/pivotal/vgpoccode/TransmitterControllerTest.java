package io.pivotal.vgpoccode;

/**
 * Created by bboe on 11/30/16.
 */
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class TransmitterControllerTest {
    private RabbitTemplate rabbitTemplate;
    private TransmitterController transmitterController;

    @Before
    public void setup() {
        rabbitTemplate = mock(RabbitTemplate.class);
        transmitterController = new TransmitterController(rabbitTemplate);
    }

    @Test
    public void testUsage() {
        assertNotNull(transmitterController.usage());
        assertTrue(transmitterController.usage().length() > 0);
    }

    @Test
    public void testSendMessage() {
        String testInput = "ABC";
        transmitterController.sendMessage(testInput);
        verify(rabbitTemplate, atLeastOnce()).convertAndSend(TransmitterController.SENDER_QUEUE, testInput);
    }
}
