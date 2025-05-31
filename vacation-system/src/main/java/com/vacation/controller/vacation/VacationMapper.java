package com.vacation.controller.vacation;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vacation.enums.DayType;
import com.vacation.module.User;
import com.vacation.module.Vacation;
import com.vacation.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VacationMapper {

  private final UserRepository userRepository;

  public VacationResponse toVacationResponse(final Vacation vacation) {
    return VacationResponse.builder()
        .id(vacation.getId())
        .addedDate(vacation.getAddedDate())
        .status(vacation.getStatus())
        .firstDate(vacation.getFirstDate())
        .firstDayType(vacation.getFirstDayType())
        .lastDate(vacation.getLastDate())
        .lastDayType(vacation.getLastDayType())
        .days(vacation.getDays())
        .approvedByD(Optional.ofNullable(vacation.getApprovedByD()).map(User::getFirstName).orElse(null))
        .approvedByM(Optional.ofNullable(vacation.getApprovedByM()).map(User::getFirstName).orElse(null))
        .build();
  }

  public Vacation toVacation(final VacationRequest vacationRequest) {

    User requestedBy = userRepository.findById(vacationRequest.requestedBy())
        .orElseThrow(() -> new EntityNotFoundException("RequestedBy user not found"));

    // if approvedByD or approvedByM is null, set it to null
    User approvedByD = vacationRequest.approvedByD() != null
        ? userRepository.findById(vacationRequest.approvedByD()).orElse(null)
        : null;

    User approvedByM = vacationRequest.approvedByM() != null
        ? userRepository.findById(vacationRequest.approvedByM()).orElse(null)
        : null;

    DayType firstDayType = vacationRequest.firstDayType();
    DayType lastDayType = vacationRequest.lastDayType();

    long daysBetween = ChronoUnit.DAYS.between(
        vacationRequest.firstDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
        vacationRequest.lastDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    ) - 1;

    if (daysBetween < 0) {
      daysBetween = 0;
    }

    double totalDays = calculateTotalDays(firstDayType, lastDayType, (int) daysBetween);

    return Vacation.builder()
        .id(vacationRequest.id())
        .addedDate(vacationRequest.addedDate())
        .status(vacationRequest.status())
        .firstDate(vacationRequest.firstDate())
        .firstDayType(vacationRequest.firstDayType())
        .lastDate(vacationRequest.lastDate())
        .lastDayType(vacationRequest.lastDayType())
        .days(totalDays)
        .requestedBy(requestedBy)
        .approvedByD(approvedByD)
        .approvedByM(approvedByM)
        .build();
  }

  private double calculateTotalDays(DayType firstDayType, DayType lastDayType, int daysBetween) {
    if (daysBetween == 0 && firstDayType == DayType.EVENING && lastDayType == DayType.MORNING) {
      return 0.5;
    }

    double totalDays = 0;

    if (firstDayType == DayType.FULL_DAY) {
      totalDays += 1.0;
    } else {
      totalDays += 0.5;
    }

    if (lastDayType == DayType.FULL_DAY) {
      totalDays += 1.0;
    } else {
      totalDays += 0.5;
    }

    totalDays += daysBetween;

    return totalDays;
  }

}
