package FirGear.com.ServiceImplementation;

import FirGear.com.Common.FitGearException;
import FirGear.com.Entity.RegisterEntity;
import FirGear.com.Models.RegisterModel;
import FirGear.com.Repository.RegisterRepository;
import FirGear.com.Services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterImp implements RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String RegisterUser(RegisterModel registerModel) {
        try {
            RegisterEntity checkIfEmailExist = registerRepository.findByEmailAddress(registerModel.getEmailAddress());
            if(checkIfEmailExist != null) {
                throw new FitGearException("Email Address is already exist.");
            }
            RegisterEntity registerEntity = new RegisterEntity();
            registerEntity.saveAll(registerModel);
            registerEntity.setPassword(passwordEncoder.encode(registerModel.getPassword()));
            registerRepository.save(registerEntity);
            return "success";
        } catch (Exception e) {
            throw e;
        }
    }
}
