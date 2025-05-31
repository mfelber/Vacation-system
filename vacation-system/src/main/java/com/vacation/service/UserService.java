package com.vacation.service;

import java.util.List;

import com.vacation.controller.user.UserResponse;

/**
 * Service interface for handling user-related operations.
 */
public interface UserService {
  /**
   * Retrieves all users in the system.
   *
   * @return a list of {@link UserResponse} representing all users
   */
  List<UserResponse> findAllUsers();

  /**
   * Retrieves all users who belong to a specific team.
   *
   * @param teamId the ID of the team
   * @return a list of {@link UserResponse} representing the users of the specified team
   */
  List<UserResponse> findUsersByTeam(Long teamId);

}
