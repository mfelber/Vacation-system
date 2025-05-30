package com.vacation.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vacation.controller.VacationMapper;
import com.vacation.controller.VacationRequest;
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
  public PageResponse<VacationResponse> findVacations(final int page, final int size) {
    Pageable pageable = PageRequest.of(page,size, Sort.by("addedDate").ascending());
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
  public List<VacationResponse> findAllVacations() {
    List<Vacation> vacations = vacationRepository.findAll();
    vacations.sort(Comparator.comparing(Vacation::getAddedDate));
    return vacations.stream()
        .map(vacationMapper::toVacationResponse)
        .toList();
  }

  @Override
  public List<VacationResponse> findVacationsByUserIdNoTeamSelected(final Long userId) {
    // check if user exists in db
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + "not found"));
    List<Vacation> vacations = vacationRepository.findAllByIdNoTeamId(userId);
    vacations.sort(Comparator.comparing(Vacation::getAddedDate));
    return vacations.stream().map(vacationMapper::toVacationResponse).toList();
  }

  @Override
  public List<VacationResponse> getVacationsByUserIdTeamSelected(final Long teamId, final Long userId) {
    // check if user exists in db
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));
    // // check if team exists in db
    Team team = teamRepository.findById(teamId)
        .orElseThrow(() -> new EntityNotFoundException("Team with id " + teamId + " not found"));
    // // check if user exists in this team in db
    if (!userRepository.existsByIdAndTeamId(userId,teamId)) {
      throw new EntityNotFoundException("User with id " + userId + " is not part of team with id " + teamId);
    }
    List<Vacation> vacations = vacationRepository.getVacationsByUserIdTeamSelected(teamId, userId);
    vacations.sort(Comparator.comparing(Vacation::getAddedDate));
    return vacations.stream().map(vacationMapper::toVacationResponse).toList();
  }

  @Override
  public List<VacationResponse> getAllVacationByTeamId(final Long teamId) {
    Team team = teamRepository.findById(teamId)
        .orElseThrow(() -> new EntityNotFoundException("Team with id " + teamId + " not found"));
    List<Vacation> vacations = vacationRepository.findByTeamId(teamId);
    vacations.sort(Comparator.comparing(Vacation::getAddedDate));
    return vacations.stream().map(vacationMapper::toVacationResponse).toList();
  }

  @Override
  public Long saveVacation(final VacationRequest vacationRequest) {
    Vacation vacation = vacationMapper.toVacation(vacationRequest);
    return vacationRepository.save(vacation).getId();
  }

  @Override
  public void deleteVacation(final Long vacationId) {
    Vacation vacation = vacationRepository.findById(vacationId)
        .orElseThrow(() -> new EntityNotFoundException("Vacation with id " + vacationId + " not found"));
    vacationRepository.delete(vacation);
  }

}
