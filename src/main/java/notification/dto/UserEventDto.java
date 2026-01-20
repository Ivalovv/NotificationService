package notification.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserEventDto {
    private String email;
    private OperationType operation;

    public enum OperationType {
        CREATED,
        DELETED
    }


}
