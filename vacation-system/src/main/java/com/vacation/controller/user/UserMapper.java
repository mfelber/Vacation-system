package com.vacation.controller.user;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vacation.module.Role;
import com.vacation.module.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMapper {

  public UserResponse toUserResponse(final User user) {
    return UserResponse.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .username(user.getUsername())
        .roles(user.getRoles().stream()
            .map(Role::getName)
            .collect(Collectors.toList()))
        .build();
  }

}
