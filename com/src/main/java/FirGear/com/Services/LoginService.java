package FirGear.com.Services;

import FirGear.com.Models.LoginModel;
import FirGear.com.Models.TokenModel;

public interface LoginService {
    public TokenModel getUser(LoginModel loginModel);
}
