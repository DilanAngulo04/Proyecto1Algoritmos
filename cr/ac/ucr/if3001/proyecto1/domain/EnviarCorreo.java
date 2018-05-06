package cr.ac.ucr.if3001.proyecto1.domain;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarCorreo {

    public static boolean enviarConGMail(String destinatario, String asunto, String mensaje) throws AddressException, MessagingException {
        //Se define la cuenta propia de la app (Empresa)
        String remitente = "leoran.auctions@gmail.com";
        String clave = "leoran.12345";

        //Se definen los datos necesarios para poder establecer una conecci'on
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", remitente);
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);

        // Quien env'ia el correo
        message.setFrom(new InternetAddress(remitente));

        // A qu'ien va dirigido
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

        //Asunto
        message.setSubject(asunto);

        //Mensaje
        message.setText(mensaje);

        Transport t = session.getTransport("smtp");
        // Se estable una conecci'on aportando los datos v'alidos de la cuenta de la empresa
        t.connect(remitente, clave);
        t.sendMessage(message, message.getAllRecipients());
        t.close();

        return true;

    }//fin m'etodo
}//fin class
