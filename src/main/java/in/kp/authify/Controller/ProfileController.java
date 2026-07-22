package in.kp.authify.Controller;

import in.kp.authify.Entity.UserEntity;
import in.kp.authify.IO.ProfileRequest;
import in.kp.authify.IO.ProfileResponse;
import in.kp.authify.IO.ResetPasswordResponse;
import in.kp.authify.Services.MailService;
import in.kp.authify.Services.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Expr;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final MailService mailService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse register(@Valid @RequestBody ProfileRequest request){
        ProfileResponse response = profileService.createProfile(request);
        mailService.sendWelcomeEmail(request.getEmail(), request.getName());
        return response;
    }

    @GetMapping("/profile")
    public ProfileResponse getProfile(@CurrentSecurityContext(expression = "authentication?.name") String email){
        return profileService.getProfile(email);
    }

    @PostMapping("/send-otp")
    public void sendVerifyOtp(@CurrentSecurityContext(expression = "authentication?.name") String email){
        try{
            profileService.sendOtp(email);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public void verifyOtp(@RequestBody Map<String, Object> request, @CurrentSecurityContext(expression = "authentication?.name") String email){
        if(request.get("otp").toString() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing OTP");
        }

        try {
            profileService.verifyOtp(email,request.get("otp").toString());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/send-reset-password")
    public void resetPassword(@Valid @RequestBody ResetPasswordResponse request){
        try {
            profileService.resetPassword(request.getEmail(),request.getOtp(), request.getPassword());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/send-reset-otp")
    public void sendResetOtp(@RequestParam String email){
        try{
            profileService.sendResetOtp(email);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
