package com.express.adapter.common.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
@Configuration
public class EmailConfig {
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_DEBUG = "mail.smtp.debug";
    private static final String MAIL_CONNECTION_TIMEOUT = "mail.smtp.connectiontimeout";
    private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";

    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;
    @Value("${spring.mail.properties.mail.smtp.debug}")
    private boolean debug;
    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
    private int connectionTimeout;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean startTlsEnable;

    /*
    JavaMailSender 는 복잡한 메시지 형식인 MIME message 를 지원해주는 인터페이스
    JavaMailSender는 MailSender 인터페이스를 상속 
    MailSender를 사용하면 단순한 메시지 형식인 SimpleMailMessage 를 전송한다.
     */
    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setPort(port);

        Properties javaMailProperties = javaMailSender.getJavaMailProperties();
        javaMailProperties.put(MAIL_SMTP_AUTH, auth);
        javaMailProperties.put(MAIL_DEBUG, debug);
        javaMailProperties.put(MAIL_CONNECTION_TIMEOUT, connectionTimeout);
        javaMailProperties.put(MAIL_SMTP_STARTTLS_ENABLE, startTlsEnable);

        javaMailSender.setJavaMailProperties(javaMailProperties);
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;
    }
}
