package com.vacation.controller;

public record VacationRequest(
    Long id,
    String addedDate,
    String status,
    String firstDate,
    String lastDate,
    String approvedByM,
    String approvedByD,
    String days

) {



}
