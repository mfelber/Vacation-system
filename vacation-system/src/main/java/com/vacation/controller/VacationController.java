package com.vacation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vacation.page.PageResponse;
import com.vacation.service.VacationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vacation")
@RequiredArgsConstructor
public class VacationController {

  // ked posielam endpoint musim skontrolovat ci sa ten user v tom time nachadza a ci sa nachadza aj v db

  private final VacationService vacationService;


  // !!!! ak chcem ziskat dovolenky z nejakeho timu a uzivatela ktory tam neni vypisat chybu ze tento uzivatel sa nenachadza v time !!!

  // all vacations no team selected and no user selected
  @GetMapping("/vacations")
  public ResponseEntity<PageResponse<VacationResponse>> getVacations(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
      @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
    return ResponseEntity.ok(vacationService.findAllVacations(page, size));
  }

  // all vacations no team selected and no user selected
  @GetMapping("/vacationss")
  public ResponseEntity<List<VacationResponse>> getVacationss() {
    return ResponseEntity.ok(vacationService.findAllVacationss());
  }

  // all vacations no team selected and user selected
  // GET /api/vacations?userId=5
  @GetMapping("/{userId}")
    public ResponseEntity<List<VacationResponse>> getVacationsByUserIdNoTeamSelected(@PathVariable final Long userId) {
      return ResponseEntity.ok(vacationService.findVacationsByUserIdNoTeamSelected(userId));
    }

  // vacation by team and selected user
  // GET /api/vacations?teamId=1&userId=5
  @GetMapping("/{teamId}/{userId}")
  public ResponseEntity<List<VacationResponse>> getVacationsByUserIdTeamSelected(
      @PathVariable final Long teamId,
      @PathVariable final Long userId){
    return ResponseEntity.ok(vacationService.getVacationsByUserIdTeamSelected(teamId, userId));
  }

  // vacations by team and all users in team
  // GET /api/vacations?teamId=1

}
