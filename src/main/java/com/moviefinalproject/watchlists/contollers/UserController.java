package com.moviefinalproject.watchlists.contollers;


import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.moviefinalproject.watchlists.entity.UserInputModel;
import com.moviefinalproject.watchlists.entity.UserModel;
import com.moviefinalproject.watchlists.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;


@Validated
@Tag(name = "Users")
@OpenAPIDefinition(info = @Info(title = "Movie Watchlists"))
@RestController
public class UserController {

  private UserService userService;
  public UserController (UserService userService) {
    this.userService = userService;
  }

  
  @Operation(summary = "Gets A User By A User Code")
  @GetMapping(value = "/users/{userCode}")
  public UserModel fetchUser(@PathVariable String userCode) {
    if ((userCode != null) && (! userCode.isEmpty())) {
      UserModel user = userService.fetchUser(userCode);
      if (user != null) {
        return user;
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User " + userCode + " not found", userCode));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Invalid Input"));
  }

  
  @Operation(summary = "Creates A New User")
  @PostMapping(value = "/users")
  @ResponseStatus(code = HttpStatus.CREATED)
  public UserModel createUser(@Valid @RequestBody UserInputModel input) {
    if((input != null) && input.isValid()) {
      UserModel user = userService.createUser(input);
      if(user != null) {
        return user;
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Input is not valid. User not created");
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Invalid user. Fields missing"));
  }

  
}
