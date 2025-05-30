package com.vacation.service;

import java.util.List;

import com.vacation.controller.VacationResponse;
import com.vacation.page.PageResponse;

public interface VacationService {

  PageResponse<VacationResponse> findAllVacations(int page, int size);

  List<VacationResponse> findAllVacationss();

  List<VacationResponse> findVacationsByUserIdNoTeamSelected(Long userId);

  List<VacationResponse> getVacationsByUserIdTeamSelected(Long teamId, Long userId);

}
