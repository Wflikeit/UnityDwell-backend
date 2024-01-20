package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.model.users.User;
import UnityDwell.com.UnityDwell.repository.OwnerOfFlatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OwnerOfFlatService implements UserDetailsService {

    private final OwnerOfFlatRepository ownerOfFlatRepository;

    @Transactional(readOnly = true)
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return ownerOfFlatRepository.findOwnerOfFlatByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException
                        (String.format("User with user email %s not found", email)));
    }
}
