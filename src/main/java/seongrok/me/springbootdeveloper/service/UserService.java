package seongrok.me.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import seongrok.me.springbootdeveloper.domain.User;
import seongrok.me.springbootdeveloper.dto.AddUserRequest;
import seongrok.me.springbootdeveloper.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Long save(AddUserRequest dto){
    return userRepository.save(User.builder()
        .email(dto.getEmail())
        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
        .build()).getId();
  }
}
