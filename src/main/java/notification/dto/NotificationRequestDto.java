package notification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Запрос на отправку уведомления по email")
@Getter
@Setter
@NoArgsConstructor
public class NotificationRequestDto {

    @Schema(
            description = "Email получателя"
    )
    private String email;

    @Schema(
            description = "Текст уведомления"
    )
    private String text;
}
