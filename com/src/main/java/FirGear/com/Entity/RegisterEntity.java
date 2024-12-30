package FirGear.com.Entity;

import FirGear.com.Models.RegisterModel;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "register")
public class RegisterEntity {
    @Id
    private String id;
    @NonNull
    private String fullName;
    @NonNull
    private String emailAddress;
    @NonNull
    private String password;

    public void saveAll(RegisterModel registerModel) {
        this.fullName = registerModel.getFullName();
        this.emailAddress = registerModel.getEmailAddress();
        this.password = registerModel.getPassword();
    }

}
