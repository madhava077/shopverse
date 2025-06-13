package com.shopverse.shopverse.Controller;

import com.shopverse.shopverse.JwtUtil;
import com.shopverse.shopverse.Dto.UserDto;
import com.shopverse.shopverse.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
private AuthenticationManager authenticationManager;

    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }
    @PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtil.generateToken(userDto.getEmail());
    String role= userService.getUserRoleByEmail(userDto.getEmail());
    Long userId = userService.getUserIdByEmail(userDto.getEmail());
    return ResponseEntity.ok(new AuthResponse(jwt,role, userId));
}
public static class AuthResponse {
    private String token;
    private String role;
    private Long userId;
    public AuthResponse(String token, String role, Long userId) {
        this.token = token;
        this.role = role;
        this.userId = userId;
    }
    public String getToken() { return token; }
    public String getRole() { return role; }
    public Long getUserId() { return userId; }
}
   
    @GetMapping("details/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
