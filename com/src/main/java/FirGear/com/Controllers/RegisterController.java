    package FirGear.com.Controllers;

    import FirGear.com.Models.RegisterModel;
    import FirGear.com.Services.RegisterService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.validation.annotation.Validated;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/register")
    @Validated
    @CrossOrigin
    public  class RegisterController {

        @Autowired
        private RegisterService registerService;

        @PostMapping(value = "/register-user")
        public String registerUser(@RequestBody RegisterModel registerModel) {
            return registerService.RegisterUser(registerModel);
        }
    }
