package program.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.dto.account.GoogleAuthDto;
import program.dto.account.LoginDto;
import program.dto.account.AuthResponseDto;
import program.dto.account.RegisterDto;
import program.services.AccountService;
import program.services.GoogleAuthService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;
    private final GoogleAuthService googleAuthService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(
            @RequestBody RegisterDto request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/google-auth")
    public ResponseEntity<AuthResponseDto> googleLogin(
            @RequestBody GoogleAuthDto googleAuth) {
        try {
            var googleUserInfo = googleAuthService.verify(googleAuth.getToken());
            //googleUserInfo.
            AuthResponseDto response = new AuthResponseDto().builder()
                    .token(googleUserInfo.getEmail())
                    .build();
            return ResponseEntity.ok(response);

        } catch (Exception ex) {

        }
        AuthResponseDto result = new AuthResponseDto().builder()
                .token("")
                .build();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> authenticate(
            @RequestBody LoginDto request
    ) {
        var auth = service.login(request);
        if(auth==null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(auth);
    }
}
