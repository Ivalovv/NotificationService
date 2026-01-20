package notification.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import notification.dto.UserEventDto;
import notification.mail.MailService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

class UserEventConsumerTest {

    private final MailService mailService = mock(MailService.class);
    private final UserEventConsumer consumer = new UserEventConsumer(mailService);
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void sendCreatedEmail() throws Exception {
        UserEventDto event = new UserEventDto("user@example.com", UserEventDto.OperationType.CREATED);
        String json = mapper.writeValueAsString(event);

        consumer.listen(json);

        verify(mailService).send("user@example.com", "Здравствуйте! Ваш аккаунт был успешно создан.");
    }

    @Test
    void sendDeletedEmail() throws Exception {
        UserEventDto event = new UserEventDto("user@example.com", UserEventDto.OperationType.DELETED);
        String json = mapper.writeValueAsString(event);

        consumer.listen(json);

        verify(mailService).send("user@example.com", "Здравствуйте! Ваш аккаунт был удалён.");
    }
}
