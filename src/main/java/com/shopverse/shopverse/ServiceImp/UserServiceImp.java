package com.shopverse.shopverse.ServiceImp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopverse.shopverse.Dto.UserDto;
import com.shopverse.shopverse.Repository.UserRepository;
import com.shopverse.shopverse.Service.UserService;
import com.shopverse.shopverse.model.User;
import com.shopverse.shopverse.Exception.UserException;
@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    public UserDto createUser(UserDto userDto) {
        User user =UserDtoTOEntity(userDto);
        User savedUser = userRepository.save(user);
        UserDto savedUserDto =EntityTOUserDto(savedUser);
        return savedUserDto;
    }
    private User UserDtoTOEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }
    private UserDto EntityTOUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }
  
        public UserDto loginUser(UserDto userDto) {
            String email = userDto.getEmail();
            String password = userDto.getPassword();
        
            if (email == null || email.isEmpty()) {
                throw new UserException("Email cannot be null or empty");
            }
            if (password == null || password.isEmpty()) {
                throw new UserException("Password cannot be null or empty");
            }
            User user = userRepository.findByEmail(email).orElseThrow(() ->new UserException(String.format("Invalid email or password")));
          
            if (!user.getPassword().equals(password)) {
                throw new UserException("Invalid password");
            }
        
            UserDto loggedInUser = EntityTOUserDto(user);
            return loggedInUser;
        }
    
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->new UserException(String.format("User with id %d not found", id)));
      
        return EntityTOUserDto(user);

    }
    public User getUserByIdReturnUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->new UserException(String.format("User with id %d not found", id)));
        return user;
    }
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() ->new UserException(String.format("User with id %d not found", id)));
        
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setRole(userDto.getRole());
            user.setAddress(userDto.getAddress());
            user.setPhoneNumber(userDto.getPhoneNumber());
            User updatedUser = userRepository.save(user);
            return EntityTOUserDto(updatedUser);
 
        }
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->new UserException(String.format("User with id %d not found", id)));
        userRepository.delete(user);
    }
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(EntityTOUserDto(user));
        }
        return userDtos;
    }
  
}
