package com.vacation.service;

import java.util.List;

import com.vacation.controller.team.TeamResponse;

/**
 * Service interface for handling operations related to teams.
 */
public interface TeamService {

  /**
   * Retrieves all teams in the system.
   *
   * @return a list of {@link TeamResponse} representing all teams
   */
  List<TeamResponse> findAllTeams();

}
