package ru.gb.eshop.security;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.eshop.entities.User;
import ru.gb.eshop.repositories.UserRepository;

import java.util.Optional;

@AllArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByPhone(s);
        if(user.isPresent()) {
            return new CustomPrincipal(user.get());
        }

        throw new UsernameNotFoundException("Пользователь не найден");
    }
}
