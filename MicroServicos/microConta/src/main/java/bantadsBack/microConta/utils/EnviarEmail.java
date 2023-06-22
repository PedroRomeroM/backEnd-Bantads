package bantadsBack.microConta.utils;

import jakarta.mail.Transport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Properties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnviarEmail {
    
    private String nomeDestinatario;
    private String emailDestinatario;

    public boolean enviarGmail() {
              
        boolean retorno = false;
        
        final String username = "bancobantads@gmail.com";
        final String password = "vrftgryvbjyixtee";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.starttls.enable", "true");
        
        jakarta.mail.Session session = jakarta.mail.Session.getInstance(prop,
                new jakarta.mail.Authenticator() {
                    @Override
                    protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new jakarta.mail.PasswordAuthentication(username, password);
                    }
                });

        try {
            jakarta.mail.Message message = new jakarta.mail.internet.MimeMessage(session);
            message.setFrom(new jakarta.mail.internet.InternetAddress("bancobantads@gmail.com"));
            message.setRecipients(
                    jakarta.mail.Message.RecipientType.TO,
                    jakarta.mail.internet.InternetAddress.parse(emailDestinatario)
            );
            
            message.setSubject("Conta Rejeitada!");
            message.setText("Olá " + nomeDestinatario + "!\n\nSua conta infelizmente nao foi aprovada!\n\n");
            Transport transport = session.getTransport("smtp");
            transport.connect();
            jakarta.mail.Transport.send(message);
            System.out.println("Done");
            retorno = true;
            
        } catch (jakarta.mail.MessagingException e) {}
        return retorno;
    }
}
