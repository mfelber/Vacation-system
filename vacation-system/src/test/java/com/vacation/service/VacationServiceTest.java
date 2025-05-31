package com.vacation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vacation.controller.vacation.VacationMapper;
import com.vacation.repository.TeamRepository;
import com.vacation.repository.UserRepository;
import com.vacation.repository.VacationRepository;

public class VacationServiceTest {

  @Mock
  private VacationRepository vacationRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private TeamRepository teamRepository;

  @Mock
  private VacationMapper vacationMapper;

  @InjectMocks
  private VacationServiceImpl vacationService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }



}
