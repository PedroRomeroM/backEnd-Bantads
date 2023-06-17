package bantadsBackEnd.microAuth.utils;

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
    private String senhaDestinatario;

    public boolean enviarGmail() {
              
        boolean retorno = false;
        
        final String username = "melhorlavanderiadomundo@gmail.com";
        final String password = "hkkoxxfwnnniycmg";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        jakarta.mail.Session session = jakarta.mail.Session.getInstance(prop,
                new jakarta.mail.Authenticator() {
                    @Override
                    protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new jakarta.mail.PasswordAuthentication(username, password);
                    }
                });

        try {
            jakarta.mail.Message message = new jakarta.mail.internet.MimeMessage(session);
            message.setFrom(new jakarta.mail.internet.InternetAddress("melhorlavanderiadomundo@gmail.com"));
            message.setRecipients(
                    jakarta.mail.Message.RecipientType.TO,
                    jakarta.mail.internet.InternetAddress.parse(emailDestinatario)
            );
            
            message.setSubject("Login e senha Bantads");
            message.setText("Olá " + nomeDestinatario + "!\n\nSegue abaixo seu login e senha para acesso ao nosso sistema:\n\nLogin: " + emailDestinatario 
                    + "\nSenha: " + senhaDestinatario + "\n\nConfirme seus dados:\n");
            jakarta.mail.Transport.send(message);
            System.out.println("Done");
            retorno = true;
            
        } catch (jakarta.mail.MessagingException e) {}
        return retorno;
    }
}
