package com.vacation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vacation.controller.team.TeamMapper;
import com.vacation.controller.team.TeamResponse;
import com.vacation.module.Team;
import com.vacation.repository.TeamRepository;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

  @Mock
  private TeamRepository teamRepository;

  @Mock
  private TeamMapper teamMapper;

  @InjectMocks
  private TeamServiceImpl teamService;

  @Test
  void shouldReturnListOfTeamResponses() {

    Team team1 = new Team();
    team1.setId(1L);
    team1.setTeamName("Team A");

    Team team2 = new Team();
    team2.setId(2L);
    team2.setTeamName("Team B");

    TeamResponse response1 = new TeamResponse();
    response1.setId(1L);
    response1.setTeamName("Team A");

    TeamResponse response2 = new TeamResponse();
    response2.setId(2L);
    response2.setTeamName("Team B");

    when(teamRepository.findAll()).thenReturn(List.of(team1, team2));
    when(teamMapper.toTeamResponse(team1)).thenReturn(response1);
    when(teamMapper.toTeamResponse(team2)).thenReturn(response2);

    List<TeamResponse> result = teamService.findAllTeams();

    assertEquals(2, result.size());
    assertEquals("Team A", result.get(0).getTeamName());
    assertEquals("Team B", result.get(1).getTeamName());

    verify(teamRepository).findAll();
    verify(teamMapper).toTeamResponse(team1);
    verify(teamMapper).toTeamResponse(team2);
  }

}
