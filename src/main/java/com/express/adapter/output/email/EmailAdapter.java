package com.express.adapter.output.email;

import com.express.application.port.output.email.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailAdapter implements EmailSender {
    private final JavaMailSender javaMailSender;

    @Override
    public void sendCertifiedCode(String email, int certifiedCode) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            makeEmailForm(mimeMessage, email, getEmailContent(certifiedCode));
        } catch (Exception e) {
            log.info("Failed to send Email");
            //추가 예외 처리 예정
            throw new RuntimeException(e);
        }
        //전송
        javaMailSender.send(mimeMessage);
    }

    public void makeEmailForm(MimeMessage mimeMessage, String email, String emailContent) throws MessagingException {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        mimeMessageHelper.setTo(email); // 메일 수신자
        mimeMessageHelper.setSubject("로지모 인증 번호"); // 메일 제목
        mimeMessageHelper.setText(emailContent, true); // 메일 본문 내용, HTML 여부
    }

    public String getEmailContent(int authCode) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        .auth-code {\n" +
                "            font-size: 24px;\n" +
                "            font-weight: bold;\n" +
                "            color: #333333;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div style=\"text-align: center;\">\n" +
                "        <h2>로지모 이메일 인증 코드</h2>\n" +
                "        <p>아래의 인증 코드를 사용하여 이메일 인증을 완료하세요:</p>\n" +
                "        <p class=\"auth-code\">" + authCode + "</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
