package FirGear.com.ServiceImplementation;

import FirGear.com.Entity.RegisterEntity;
import FirGear.com.Models.RegisterModel;
import FirGear.com.Repository.RegisterRepository;
import FirGear.com.Services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterImp implements RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

        @Override
        public String RegisterUser(RegisterModel registerModel) {
            try {
                RegisterEntity registerEntity = new RegisterEntity();
                registerEntity.saveAll(registerModel);
                registerRepository.save(registerEntity);
                return "success";
            } catch (Exception e) {
                throw e;
            }
        }
}
