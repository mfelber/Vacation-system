package com.vacation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vacation.controller.team.TeamMapper;
import com.vacation.controller.team.TeamResponse;
import com.vacation.module.Team;
import com.vacation.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

  private final TeamRepository teamRepository;

  private final TeamMapper teamMapper;

  @Override
  public List<TeamResponse> findAllTeams() {
    List<Team> teams = teamRepository.findAll();
    return teams.stream()
        .map(teamMapper::toTeamResponse)
        .toList();
  }

}
