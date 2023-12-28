package by.tms.blogapic22onl.web.controller;

import by.tms.blogapic22onl.service.AuthenticationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping( "login")
    public ResponseEntity<AuthenticationService.JwtResponse>login(@RequestBody AuthenticationService.JwtRequest authRequest){
        final AuthenticationService.JwtResponse token= authenticationService.login(authRequest) ;
        return ResponseEntity.ok(token);
    }
    @PostMapping("token")
    public ResponseEntity<AuthenticationService.JwtResponse>getNewUpdateToken(@RequestBody UpdateJwtRequest request){
        final AuthenticationService.JwtResponse token = authenticationService.getAccessToken(request.getUpdateToken());
        return ResponseEntity.ok(token);
    }
    @PostMapping("update")
    public ResponseEntity<AuthenticationService.JwtResponse>getNewUpdateToken(@RequestBody UpdateJwtRequest request){
       final AuthenticationService.JwtResponse token = authenticationService.update(request.getUpdateToken());
        return ResponseEntity.ok(token);
    }
}
