package com.qqmail;

import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;
  
public class MailUtils {  
	//qq邮件平台
	public static final String SMTPHOST="smtp.qq.com";  
	//发信邮箱
	public static final String USERNAME="1174963032@qq.com";
	//P0P3安全码  hexsmgybkhgwfeeb
	public static final String PASSWORD="hexsmgybkhgwfeeb";//输入16位授权码
	//邮箱编码
	public static final String MESSAGETYPE="text/html;charset=gb2312";
	
    @SuppressWarnings("static-access")  
    private static boolean sendMessage(MailEntity entity ) throws MessagingException, GeneralSecurityException {  
        // 第一步：配置javax.mail.Session对象  
       // System.out.println("为" + SMTPHOST + "配置mail session对象");  
    	
    	
         //SSL认证
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
      
        Properties props = new Properties();  
        props.put("mail.smtp.ssl.enable", "true");//SLL
        props.put("mail.smtp.host", SMTPHOST);  
        props.put("mail.smtp.starttls.enable","true");//使用 STARTTLS安全连接  
        //props.put("mail.smtp.port", "25");             //google使用465或587端口  
        props.put("mail.smtp.auth", "true");        // 使用验证  
        props.put("mail.debug", "true");  
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        Session mailSession = Session.getInstance(props,new MyAuthenticator(USERNAME,PASSWORD));  
  
        // 第二步：编写消息  
       // System.out.println("编写消息from——to:" + USERNAME + "——" + to);  
  
        InternetAddress fromAddress = new InternetAddress(USERNAME);  
        InternetAddress toAddress = new InternetAddress(entity.getTo());  
  
        MimeMessage message = new MimeMessage(mailSession);  
  
        message.setFrom(fromAddress);  
        message.addRecipient(RecipientType.TO, toAddress);   
        message.setSentDate(Calendar.getInstance().getTime());  
        message.setSubject(entity.getSubject());  
        message.setContent(entity.getMessageText(), MESSAGETYPE);  
  
        // 第三步：发送消息  
        Transport transport = mailSession.getTransport("smtp");  
        transport.connect(SMTPHOST,USERNAME, PASSWORD);  
        transport.send(message, message.getRecipients(RecipientType.TO));  
        
        return true;
    }  
  
    public static boolean sendMail(MailEntity entity){
    	boolean sended = false;
        try {  
            sended = sendMessage(entity);  
        } catch (Exception e) {  
           e.printStackTrace();
        }  
        if(sended)
        	System.out.println("发送成功");
        else
        	System.out.println("发送失败");
        return sended;
    }
}  
class MyAuthenticator extends Authenticator{  
    String userName="";  
    String password="";  
    public MyAuthenticator(){  
          
    }  
    public MyAuthenticator(String userName,String password){  
        this.userName=userName;  
        this.password=password;  
    }  
     protected PasswordAuthentication getPasswordAuthentication(){     
        return new PasswordAuthentication(userName, password);     
     }   
} 
