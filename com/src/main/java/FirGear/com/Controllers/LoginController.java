package FirGear.com.Controllers;


import FirGear.com.Models.LoginModel;
import FirGear.com.Models.TokenModel;
import FirGear.com.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Validated
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/user")
    public TokenModel loginUser(@RequestBody LoginModel loginModel) {
        return loginService.getUser(loginModel);
    }
}
