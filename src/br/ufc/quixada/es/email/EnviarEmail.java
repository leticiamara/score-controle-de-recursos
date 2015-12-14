package br.ufc.quixada.es.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EnviarEmail {

	 private String mailSMTPServer;  
	 private String mailSMTPServerPort;
	 
	
	 public EnviarEmail(){
		 mailSMTPServer = "smtp.gmail.com";  
	     mailSMTPServerPort = "465";
	 }
	 
	 public void enviar(String destino, String assunto, String mensagem) {  
         
	        Properties props = new Properties();  
	  
	        
	        props.put("mail.transport.protocol", "smtp"); //protocolo de envio como SMTP  
	        props.put("mail.smtp.starttls.enable","true");   
	        props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL  
	        props.put("mail.smtp.auth", "true"); //ativa autenticacao  
	        props.put("mail.smtp.user", "scoreufc@gmail.com"); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)  
	        props.put("mail.debug", "true");  
	        props.put("mail.smtp.port", mailSMTPServerPort); //porta  
	        props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket  
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
	        props.put("mail.smtp.socketFactory.fallback", "false");  
	          
	        //Cria um autenticador que sera usado a seguir  
	        Autenticacao aut = null;  
	        aut = new Autenticacao ("scoreufc@gmail.com","scoreufcweb");  
	          
	        //faz a autenticacao  
	        Session session = Session.getDefaultInstance(props, aut);  
	       // Session session = Session.getDefaultInstance(props, auth);  
	        //session.setDebug(true); //Habilita o LOG das ações executadas durante o envio do email  
	  
	      
	        Message msg = new MimeMessage(session);  
	  
	        try {  
	            //setando o destinario  
	            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(destino));  
	            //Setando a origem do email  
	            msg.setFrom(new InternetAddress("scoreufc@gmail.com"));  
	            //Setando o assunto  
	            msg.setSubject(assunto);  
	            //Setando o conteúdo/corpo do email  
	            msg.setContent(mensagem,"text/plain");  
	  
	        } catch (Exception e) {  
	            System.out.println("Erro ao enviar Email");  
	            e.printStackTrace();  
	        }  
	          
	        // enviar os dados para o email  
	        Transport tr;  
	        try {  
	            tr = session.getTransport("smtp"); //define smtp para transporte  
	             
	            tr.connect(mailSMTPServer, "scoreufc@gmail.com", "scoreufcweb");  
	            msg.saveChanges(); // don't forget this  
	            //envio da mensagem  
	            tr.sendMessage(msg, msg.getAllRecipients());  
	            tr.close();  
	        } catch (Exception e) {  
	           
	            System.out.println("Erro ao enviar Email");  
	            e.printStackTrace();  
	        }  
	  
	    }  
	}  
	  
	
	class Autenticacao extends Authenticator {  
	    public String username = null;  
	    public String password = null;  
	  
	  
	    public Autenticacao(String user, String pwd) {  
	        username = user;  
	        password = pwd;  
	    }  
	  
	    protected PasswordAuthentication getPasswordAuthentication() {  
	        return new PasswordAuthentication (username,password);  
	    }  
	    
}