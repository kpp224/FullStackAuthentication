package in.kp.authify.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.jdbc.object.UpdatableSqlQuery;

import java.sql.Timestamp;

@Entity
@Table(name = "authify_app")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String userId;
    @Column(unique = true)
    private String email;
    private String password;
    private String verifyOtp;
    private String resetOtp;
    private Boolean isAccountVerified;
    private Long verifyOtpExpiredAt;
    private Long resetOtpExpiredAt;

    @CreationTimestamp
    @Column(updatable = true)
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;


}
