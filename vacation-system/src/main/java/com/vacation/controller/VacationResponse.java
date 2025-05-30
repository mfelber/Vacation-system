package com.vacation.controller;

import java.util.Date;

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
  private Date lastDate;
  private Double days;
  private String approvedByD;
  private String approvedByM;
}
