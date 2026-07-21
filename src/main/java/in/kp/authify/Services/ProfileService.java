package in.kp.authify.Services;

import in.kp.authify.Entity.UserEntity;
import in.kp.authify.IO.ProfileRequest;
import in.kp.authify.IO.ProfileResponse;

import java.util.List;

public interface ProfileService {
    ProfileResponse createProfile(ProfileRequest request);
    ProfileResponse getProfile(String email);
    void sendResetOtp(String email);
}
