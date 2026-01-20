package notification.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import notification.dto.UserEventDto;
import notification.mail.MailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventConsumer {

    private final MailService mailService;

    @KafkaListener(topics = "user-events", groupId = "notification-service")
    public void listen(String message) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        UserEventDto event = mapper.readValue(message, UserEventDto.class);

        switch (event.getOperation()) {
            case CREATED -> mailService.send(
                    event.getEmail(),
                    "Здравствуйте! Ваш аккаунт был успешно создан."
            );

            case DELETED -> mailService.send(
                    event.getEmail(),
                    "Здравствуйте! Ваш аккаунт был удалён."
            );

            default -> {
                return;
            }
        }
    }
}
