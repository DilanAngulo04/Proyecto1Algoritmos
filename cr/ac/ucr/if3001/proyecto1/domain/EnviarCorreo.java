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
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
        String remitente = "leoran.auctions@gmail.com";  //Para la dirección nomcuenta@gmail.com
        String clave = "leoran.12345";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", remitente);
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);

// Quien envia el correo
        message.setFrom(new InternetAddress(remitente));

// A quien va dirigido
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        message.setSubject(asunto);
        message.setText(mensaje);
//        BodyPart texto = new MimeBodyPart();
//// Texto del mensaje
//        texto.setText("Correo de prueba");

//        BodyPart adjunto = new MimeBodyPart();

// Cargamos la imagen
//        adjunto.setDataHandler(new DataHandler(new FileDataSource("/assets/normal.png")));

// Opcional. De esta forma transmitimos al receptor el nombre original del
// fichero de imagen.
//        adjunto.setFileName("/assets/normal.png");

//        MimeMultipart multiParte = new MimeMultipart();
//        multiParte.addBodyPart(texto);
//        multiParte.addBodyPart(adjunto);
//
//        MimeMessage message = new MimeMessage(session);
//
//// Se rellena el From
//        message.setFrom(new InternetAddress(remitente));
//// Se rellenan los destinatarios
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress(remitente));
//// Se rellena el subject
//        message.setSubject("Hola, correo de prueba");
// Se mete el texto y la foto adjunta.
//        message.setContent(multiParte);

        Transport t = session.getTransport("smtp");
// Aqui usuario y password de gmail
        t.connect(remitente, clave);
        t.sendMessage(message, message.getAllRecipients());
        t.close();
        
        return true;

    }

//    public static void main(String[] args) throws MessagingException {        
        //enviarConGMail();
//    }
}
