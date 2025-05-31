package com.vacation.controller.vacation;

import java.util.Date;

import com.vacation.enums.DayType;
import com.vacation.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacationResponse {

  private Long id;
  private Date addedDate;
  private Status status;
  private Date firstDate;
  private DayType firstDayType;
  private Date lastDate;
  private DayType lastDayType;
  private Double days;
  private String approvedByD;
  private String approvedByM;
}
