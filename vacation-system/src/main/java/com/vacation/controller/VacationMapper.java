package com.vacation.controller;

import org.springframework.stereotype.Service;

import com.vacation.module.Vacation;

@Service
public class VacationMapper {

  public VacationResponse toVacationResponse(final Vacation vacation) {
    return VacationResponse.builder()
        .id(vacation.getId())
        .addedDate(vacation.getAddedDate())
        .status(vacation.getStatus())
        .firstDate(vacation.getFirstDate())
        .lastDate(vacation.getLastDate())
        .days(vacation.getDays())
        .approvedByD(null) // Assuming you will set this later
        .approvedByM(null) // Assuming you will set this later
        .build();
  }

}
