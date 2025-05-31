package com.vacation.controller.team;

import org.springframework.stereotype.Service;

import com.vacation.module.Team;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamMapper {

  public TeamResponse toTeamResponse(final Team team) {
    return TeamResponse.builder()
        .id(team.getId())
        .teamName(team.getTeamName())
        .build();
  }

}
