package com.vacation.controller.team;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vacation.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

  private final TeamService teamService;

  // get all teams
  @GetMapping("/all-teams")
  public ResponseEntity<List<TeamResponse>> getTeams() {
    return ResponseEntity.ok(teamService.findAllTeams());
  }

}
