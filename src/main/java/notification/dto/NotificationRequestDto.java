package notification.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class NotificationRequestDto {

    private String email;
    private String text;
}
