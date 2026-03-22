package com.example.demoTcc.Controller;
import com.example.demoTcc.Model.Email;
import com.example.demoTcc.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apiEmail")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Endpoint: POST http://localhost:8080/api/email/send-reminder
    @PostMapping("/enviar")
    public ResponseEntity<String> sendReminder(@RequestBody Email request) {
        String recipientEmail = request.getRecipientEmail();

        if (recipientEmail == null || recipientEmail.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("E-mail do destinatário é obrigatório.");
        }

        boolean success = emailService.sendReminder(recipientEmail);

        if (success) {
            return ResponseEntity.ok("Lembrete de monitoria enviado com sucesso para: " + recipientEmail);
        } else {
            return ResponseEntity.status(500).body("Falha ao enviar o e-mail de lembrete.");
        }
    }
}
