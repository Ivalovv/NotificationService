package notification.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import notification.dto.NotificationRequestDto;
import notification.mail.MailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    private final MailService mailService;

    public NotificationController(MailService mailService) {
        this.mailService = mailService;
    }

    @Operation(
            summary = "Отправить уведомление по email",
            description = "Отправляет уведомление по email на указанный email."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Email успешно отправлено"),
            @ApiResponse(responseCode = "400", description = "Неверный запрос")
    })
    @PostMapping
    public void send(@RequestBody NotificationRequestDto dto) {
        mailService.send(dto.getEmail(), dto.getText());
    }
}
