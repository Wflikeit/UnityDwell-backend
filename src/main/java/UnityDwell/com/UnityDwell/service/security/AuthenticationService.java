//package UnityDwell.com.UnityDwell.service.security;
//
//
//import UnityDwell.com.UnityDwell.config.security.JWTService;
//import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
//import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//
//    private final UserService userService;
//    private final AuthenticationManager authenticationManager;
//    private final JWTService jwtService;
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//
//        String email = request.getEmail().toLowerCase();
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        email,
//                        request.getPassword()));
//
//        User user = (User) userService.loadUserByUsername(email);
//
//        String accessToken = jwtService.generateJWTToken(user);
//        return AuthenticationResponse.builder()
//                .accessToken(accessToken)
//                .build();
//    }
//}
