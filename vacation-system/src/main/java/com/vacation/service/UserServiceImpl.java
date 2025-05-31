package com.vacation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vacation.controller.user.UserMapper;
import com.vacation.controller.user.UserResponse;
import com.vacation.module.Team;
import com.vacation.module.User;
import com.vacation.repository.TeamRepository;
import com.vacation.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final TeamRepository teamRepository;

  private final UserMapper userMapper;

  @Override
  public List<UserResponse> findAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(userMapper::toUserResponse)
        .toList();
  }

  @Override
  public List<UserResponse> findUsersByTeam(Long teamId) {
    Team team = teamRepository.findById(teamId)
        .orElseThrow(() -> new EntityNotFoundException("Team with id " + teamId + " not found"));
    List<User> users = userRepository.findAllByTeamId(teamId);
    return users.stream()
        .map(userMapper::toUserResponse)
        .toList();
  }

}
