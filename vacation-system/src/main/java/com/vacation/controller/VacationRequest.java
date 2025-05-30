package com.vacation.controller;

import java.util.Date;

import com.vacation.enums.DayType;
import com.vacation.enums.Status;

public record VacationRequest(
    Long id,
    Date addedDate,
    Status status,
    Date firstDate,
    DayType firstDayType,
    Date lastDate,
    DayType lastDayType,
    Long approvedByM,
    Long approvedByD,
    Long requestedBy,
    Double days

) {
}
