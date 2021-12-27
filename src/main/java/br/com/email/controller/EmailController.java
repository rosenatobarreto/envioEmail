package br.com.email.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class EmailController {
	
	@Autowired private JavaMailSender mailSender;

    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Spring Boot Application. Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        message.setTo("inserir o e-mail aqui");//Email que recebe a mensagem
        message.setFrom("inserir o e-mail aqui");//Email que envia a mensagem

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
    //e-mails em formato HTML 
    @RequestMapping(path = "/email-send-html", method = RequestMethod.GET)
    public String sendMailHtml() {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( "inserir o e-mail aqui" );//Email que recebe a mensagem
            helper.setSubject( "Teste Envio de e-mail" );
            helper.setText("<p>Spring Boot Application. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>", true);
            mailSender.send(mail);

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
    }
	
}
