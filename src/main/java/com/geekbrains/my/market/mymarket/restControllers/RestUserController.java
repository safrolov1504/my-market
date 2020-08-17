package com.geekbrains.my.market.mymarket.restControllers;

import com.geekbrains.my.market.mymarket.model.User;
import com.geekbrains.my.market.mymarket.model.dtos.SystemUser;
import com.geekbrains.my.market.mymarket.services.UserService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/users")
@Api("Set of endpoints for CRUD operations for Users")
public class RestUserController {
    private UserService userService;

    @Autowired
    private Gson gson;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/new_user", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a new user")
    public Object saveNewUser(@RequestBody @Validated SystemUser systemUser,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new Gson().toJson(bindingResult);
        }
        Optional<User> existing = userService.findUserByName(systemUser.getName());
        if (existing.isPresent()) {
            FieldError error = new FieldError("registrationError", "Name",
                    "User with phone number: [" + systemUser.getName() + "] is already exist");
            bindingResult.addError(error);
            //List<ObjectError> allErrors = bindingResult.getAllErrors();
            return new Gson().toJson(bindingResult);
        }
        User user = userService.save(systemUser);
        return user;
    }
}
