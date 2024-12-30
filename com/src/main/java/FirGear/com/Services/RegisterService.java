package FirGear.com.Services;

import FirGear.com.Common.ApiResponse;
import FirGear.com.Entity.RegisterEntity;
import FirGear.com.Models.RegisterModel;

public interface RegisterService {
    public String RegisterUser(RegisterModel registerModel);
}
