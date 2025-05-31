package com.vacation.service;

import java.util.List;

import com.vacation.controller.vacation.VacationRequest;
import com.vacation.controller.vacation.VacationResponse;
import com.vacation.page.PageResponse;

import jakarta.validation.Valid;

/**
 * Service interface for managing vacations.
 */
public interface VacationService {

  /**
   * Retrieves a paginated list of all vacations.
   *
   * @param page the page number (0-based)
   * @param size the number of records per page
   * @return a paginated list of vacation responses
   */
  PageResponse<VacationResponse> findVacations(int page, int size);

  /**
   * Retrieves all vacations without selecting any team and user.
   *
   * @return a list of all vacation responses
   */
  List<VacationResponse> findAllVacations();

  /**
   * Retrieves vacations for a user when no team is selected.
   *
   * @param userId the ID of the user
   * @return a list of vacation responses for the user
   */
  List<VacationResponse> findVacationsByUserIdNoTeamSelected(Long userId);

  /**
   * Retrieves vacations for a user when a specific team is selected.
   *
   * @param teamId the ID of the team
   * @param userId the ID of the user
   * @return a list of vacation responses for the user and team
   */
  List<VacationResponse> getVacationsByUserIdTeamSelected(Long teamId, Long userId);

  /**
   * Retrieves all vacations associated with a specific team and no user selected.
   *
   * @param teamId the ID of the team
   * @return a list of vacation responses for the team
   */
  List<VacationResponse> getAllVacationByTeamId(Long teamId);

  /**
   * Saves a new vacation request.
   *
   * @param vacationRequest the vacation request data
   * @return the ID of the saved vacation
   */
  Long saveVacation(@Valid VacationRequest vacationRequest);

  /**
   * Deletes a vacation by its ID.
   *
   * @param vacationId the ID of the vacation to delete
   */
  void deleteVacation(Long vacationId);

}
