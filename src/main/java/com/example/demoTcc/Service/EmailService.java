package com.example.demoTcc.Service;
import com.example.demoTcc.Model.Monitoria;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}") // Pega o e-mail de envio para usar no "From"
    private String senderEmail;

    @Value("${monitoria.api.base-url}")
    private String monitoriaApiBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    // ----------------------------------------------------
    // MÉTODO: Consulta à API de Monitoria (Retorna o PRÓXIMO AGENDAMENTO)
    // ----------------------------------------------------
    public Optional<Monitoria> buscarProximaMonitoria(String emailAluno) {
        // Constrói a URL: http://[monitoriaApiBaseUrl]/Email/{emailAluno}
        String url = UriComponentsBuilder.fromUriString(monitoriaApiBaseUrl)
                .path("/Email/{email}")
                .buildAndExpand(emailAluno)
                .toUriString();

        try {
            // Requisição GET que espera uma LISTA de Monitoria (List<Monitoria>)
            ResponseEntity<List<Monitoria>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Monitoria>>() {} // Necessário para desserializar listas
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<Monitoria> monitorias = response.getBody();

                // Filtra para encontrar a primeira monitoria que não esteja 'CONCLUÍDA'
                return monitorias.stream()
                        .filter(m -> !"CONCLUÍDA".equalsIgnoreCase(m.getSituacao()))
                        .findFirst(); // Pega a primeira que encontrar
            }
            return Optional.empty();

        } catch (HttpClientErrorException.NotFound e) {
            // Trata o caso de 404 (se a sua API lançar 404 quando a lista estiver vazia)
            System.err.println("Nenhuma monitoria encontrada para o aluno: " + emailAluno);
            return Optional.empty();
        }
        catch (Exception e) {
            System.err.println("Erro ao buscar monitoria na API externa: " + e.getMessage());
            return Optional.empty();
        }
    }

    // ----------------------------------------------------
    // MÉTODO MODIFICADO: Envio de E-mail
    // ----------------------------------------------------
    public boolean sendReminder(String recipientEmail) {
        // 1. BUSCA O PRÓXIMO AGENDAMENTO
        Optional<Monitoria> monitoriaOpt = buscarProximaMonitoria(recipientEmail);

        if (monitoriaOpt.isEmpty()) {
            System.err.println("Monitoria ATIVA não encontrada ou e-mail inválido para: " + recipientEmail);
            return false;
        }

        Monitoria monitoria = monitoriaOpt.get(); // Pega o objeto Monitoria

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(senderEmail, "Sistema de Monitoria - COTIL");
            helper.setTo(recipientEmail);
            helper.setSubject("Lembrete: Monitoria de " + monitoria.getAssunto() + " Agendada! 🗓️");

            // Define a informação de Local/Link baseada no 'Modo'
            String localOuLink = monitoria.getModo().equalsIgnoreCase("REMOTO")
                    ? "Modalidade Online. Entre em contato com o monitor " + monitoria.getEmailMonitor() + " para o link."
                    : "Local Físico. Consulte o monitor " + monitoria.getEmailMonitor() + " para a sala exata.";

            // CONTEÚDO HTML PERSONALIZADO
            String htmlContent = """
                <html>
                <body style="font-family: sans-serif; color: #1e293b;">
                    <div style="background-color: #f1f5f9; padding: 25px; border-radius: 12px;">
                        <h2 style="color: #0f172a;">Olá Aluno(a)!</h2>
                        <p>Este é um lembrete automático da sua próxima sessão de monitoria agendada:</p>
                        
                        <div style="background-color: #ffffff; padding: 20px; border-radius: 10px; border-left: 5px solid #3B82F6;">
                          <h3 style="color: #3B82F6; margin-top: 0;">Detalhes da Monitoria:</h3>
                          <ul style="list-style-type: none; padding-left: 0;">
                            <li><strong>Assunto:</strong> %s</li>
                            <li><strong>Monitor(a):</strong> %s</li>
                            <li><strong>Horário Agendado:</strong> %s</li>
                            <li><strong>Modalidade:</strong> %s</li>
                            <li><strong>Local/Link:</strong> %s</li>
                          </ul>
                        </div>
                        
                        <p> Por favor, prepare-se para o encontro. 😊</p>
                        <p>Atenciosamente,<br>A Equipe do Sistema de Monitoria.</p>
                    </div>
                </body>
                </html>
            """.formatted(
                    monitoria.getAssunto(),
                    monitoria.getEmailMonitor(),
                    monitoria.getHorario(),
                    monitoria.getModo(),
                    localOuLink

            );

            helper.setText(htmlContent, true);
            mailSender.send(message);

            return true;
        } catch (Exception e) {
            System.err.println("Falha ao enviar e-mail para " + recipientEmail + ": " + e.getMessage());
            return false;
        }
    }
}