package com.vacation.controller.vacation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vacation.page.PageResponse;
import com.vacation.service.VacationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vacation")
@RequiredArgsConstructor
public class VacationController {

  private final VacationService vacationService;

  // all vacations no team selected and no user selected
  @GetMapping("/vacations")
  public ResponseEntity<PageResponse<VacationResponse>> getAllVacations(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
      @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
    return ResponseEntity.ok(vacationService.findVacations(page, size));
  }

  // all vacations no team selected and no user selected
  @GetMapping("/all-vacations")
  public ResponseEntity<List<VacationResponse>> getVacations() {
    return ResponseEntity.ok(vacationService.findAllVacations());
  }

  // all vacations no team selected and user selected
  // GET /api/vacations?userId=5
  @GetMapping("/user/{userId}")
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
  @GetMapping("/team/{teamId}")
  public ResponseEntity<List<VacationResponse>> getVacationsByTeamId(@PathVariable final Long teamId) {
    return ResponseEntity.ok(vacationService.getAllVacationByTeamId(teamId));
  }

  @PostMapping("/save-vacation")
  public ResponseEntity<Long> saveVacation(@Valid @RequestBody VacationRequest vacationRequest) {
    return ResponseEntity.ok(vacationService.saveVacation(vacationRequest));
  }

  @DeleteMapping("/delete-vacation/{vacationId}")
  public ResponseEntity<Void> deleteVacation(@PathVariable Long vacationId) {
    vacationService.deleteVacation(vacationId);
    return ResponseEntity.noContent().build();
  }

}
