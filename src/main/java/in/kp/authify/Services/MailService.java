package in.kp.authify.Services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String fromEmail;

    public void sendWelcomeEmail(String toEmail, String name){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Welcome to our platform");
        message.setText("Hello " + name + ",\n\nThanks for registration for us! \n\nRegards, \nAuthify Team");
        mailSender.send(message);
    }

    public void sendResetOtpEmail(String toEmail, String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Password reset OTP");
        message.setText("Your OTP for resetting a Password is " + otp + ". Use this OTP for resetting your Password.");
        mailSender.send(message);
    }

    public void sendOtp(String toEmail, String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom(fromEmail);
        message.setSubject("Verify OTP");
        message.setText("Your verify OTP is " + otp + ". Use this OTP to verify your Account");
        mailSender.send(message);
    }
}
