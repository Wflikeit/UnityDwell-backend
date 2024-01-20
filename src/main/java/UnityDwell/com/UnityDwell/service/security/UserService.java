//package UnityDwell.com.UnityDwell.service.security;
//
//import com.sourceryacademy.apartmentbooking.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class UserService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Transactional(readOnly = true)
//    @Override
//    public UserDetails loadUserByMail(String userEmail) throws UsernameNotFoundException {
//
//        return userRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new UsernameNotFoundException
//                        (String.format("User with user email %s not found", userEmail)));
//    }
//
//    @Transactional(readOnly = true)
//    public void checkExistUserByEmail(String email) {
//        if (!userRepository.existsByEmail(email)) {
//            throw new UsernameNotFoundException(String.format("User with user email %s not found", email));
//        }
//    }
//}
