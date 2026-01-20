package notification.controller;

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

    @PostMapping
    public void send(@RequestBody NotificationRequestDto dto) {
        mailService.send(dto.getEmail(), dto.getText());
    }
}
