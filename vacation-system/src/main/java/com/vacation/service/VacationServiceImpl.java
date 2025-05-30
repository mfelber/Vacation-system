package com.vacation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vacation.controller.VacationMapper;
import com.vacation.controller.VacationResponse;
import com.vacation.module.Team;
import com.vacation.module.User;
import com.vacation.module.Vacation;
import com.vacation.page.PageResponse;
import com.vacation.repository.TeamRepository;
import com.vacation.repository.UserRepository;
import com.vacation.repository.VacationRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService {

  private final VacationRepository vacationRepository;

  private final UserRepository userRepository;

  private final TeamRepository teamRepository;

  private final VacationMapper vacationMapper;

  @Override
  public PageResponse<VacationResponse> findAllVacations(final int page, final int size) {
    Pageable pageable = PageRequest.of(page,size, Sort.by("addedDate").descending());
    Page<Vacation> vacation = vacationRepository.findAll(pageable);
    List<VacationResponse> vacationResponses = vacation.stream().map(vacationMapper::toVacationResponse).toList();
    return new PageResponse<>(
        vacationResponses,
        vacation.getNumber(),
        vacation.getSize(),
        vacation.getTotalElements(),
        vacation.getTotalPages(),
        vacation.isLast(),
        vacation.isFirst());

  }

  @Override
  public List<VacationResponse> findAllVacationss() {
    List<Vacation> vacations = vacationRepository.findAll();
    return vacations.stream()
        .map(vacationMapper::toVacationResponse)
        .toList();
  }

  @Override
  public List<VacationResponse> findVacationsByUserIdNoTeamSelected(final Long userId) {
    // check if user exists in db
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User with this id not found"));
    List<Vacation> vacations = vacationRepository.findAllByIdNoTeamId(userId);
    return vacations.stream().map(vacationMapper::toVacationResponse).toList();
  }

  @Override
  public List<VacationResponse> getVacationsByUserIdTeamSelected(final Long teamId, final Long userId) {
    // check if user exists in db
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User with this id not found"));
    // // check if team exists in db
    Team team = teamRepository.findById(teamId)
        .orElseThrow(() -> new EntityNotFoundException("Team with this id not found"));
    // // check if user exists in this team in db
    if (!userRepository.existsByIdAndTeamId(userId,teamId)) {
      throw new EntityNotFoundException("User with this id not found in this team");
    }
    List<Vacation> vacations = vacationRepository.getVacationsByUserIdTeamSelected(teamId, userId);
    return vacations.stream().map(vacationMapper::toVacationResponse).toList();
  }

}
