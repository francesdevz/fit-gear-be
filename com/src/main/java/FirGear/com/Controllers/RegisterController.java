package FirGear.com.Controllers;

import FirGear.com.Models.RegisterModel;
import FirGear.com.Services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * RegisterController is responsible for handling the user registration process.
 * It receives a POST request with user registration data and delegates the process to the RegisterService.
 */
@RestController
@RequestMapping("/register")
@Validated // Ensures that input validation annotations are processed
@CrossOrigin // Allows cross-origin requests (useful for handling requests from a different domain)
public class RegisterController {

    /**
     * The RegisterService instance that handles business logic for user registration.
     */
    @Autowired
    private RegisterService registerService;

    /**
     * Handles the POST request to register a new user.
     *
     * This method accepts a JSON object representing user registration data,
     * validates it, and then delegates the process to the RegisterService.
     *
     * @param registerModel the registration data received in the request body
     * @return a response message indicating the result of the registration process
     */
    @PostMapping(value = "/register-user")
    public String registerUser(@RequestBody RegisterModel registerModel) {
        // Call the RegisterService to handle the user registration process
        return registerService.RegisterUser(registerModel);
    }
}
