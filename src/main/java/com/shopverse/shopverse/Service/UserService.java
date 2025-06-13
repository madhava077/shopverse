package com.shopverse.shopverse.Service;

import java.util.List;

import com.shopverse.shopverse.Dto.UserDto;
import com.shopverse.shopverse.model.User;

public interface UserService {
    public UserDto createUser(UserDto userDto);

    public List<UserDto> getAllUsers();

    public void deleteUser(Long id);

    public UserDto updateUser(Long id, UserDto userDto);
    public User getUserByIdReturnUser(Long id);
    public UserDto getUserById(Long id);
    public UserDto loginUser(UserDto userDto);
   public String getUserRoleByEmail(String email);
   public Long getUserIdByEmail(String email);
}
