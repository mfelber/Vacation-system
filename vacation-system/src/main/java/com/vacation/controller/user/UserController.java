package com.vacation.controller.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.vacation.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  //get all users
  // no team selected and no user selected
  @GetMapping("/all-users")
  public ResponseEntity<List<UserResponse>> getUsers() {
    return ResponseEntity.ok(userService.findAllUsers());
  }

  //get users by team id
  @GetMapping("/team/{teamId}")
  public ResponseEntity<List<UserResponse>> getUsersByTeam(@PathVariable Long teamId) {
    return ResponseEntity.ok(userService.findUsersByTeam(teamId));
  }

}
