package com.vacation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.vacation.controller.vacation.VacationRequest;
import com.vacation.controller.vacation.VacationResponse;
import com.vacation.enums.DayType;
import com.vacation.enums.Status;
import com.vacation.module.User;
import com.vacation.repository.UserRepository;
import com.vacation.repository.VacationRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class VacationServiceImplIntegrationTest {

  @Autowired
  private VacationService vacationService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private VacationRepository vacationRepository;

  @Test
  void shouldSaveVacationAndFindByUserIdWithoutTeam() {

    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setUserName("johndoe");
    user = userRepository.save(user);

    VacationRequest request = new VacationRequest(
        null,
        new Date(),
        Status.PENDING,
        Date.from(LocalDate.of(2025, 7, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
        DayType.FULL_DAY,
        Date.from(LocalDate.of(2025, 7, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()),
        DayType.FULL_DAY,
        null,
        null,
        user.getId(),
        15.0
    );

    Long vacationId = vacationService.saveVacation(request);

    List<VacationResponse> vacations = vacationService.findVacationsByUserIdNoTeamSelected(user.getId());

    vacations.forEach(vacation -> {
      System.out.println("Vacation:");
      System.out.println("  ID: " + vacation.getId());
      System.out.println("  From: " + vacation.getFirstDate());
      System.out.println("  To: " + vacation.getLastDate());
      System.out.println("  Days: " + vacation.getDays());
      System.out.println("  Status: " + vacation.getStatus());
      System.out.println("  -------------------");
    });

    assertEquals(1, vacations.size());
    VacationResponse vacation = vacations.get(0);
    assertNull(user.getTeam());

    Date expectedFirstDate = Date.from(LocalDate.of(2025, 7, 1)
        .atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date expectedLastDate = Date.from(LocalDate.of(2025, 7, 15)
        .atStartOfDay(ZoneId.systemDefault()).toInstant());

    Status expectedStatus = Status.PENDING;

    assertEquals(expectedFirstDate, vacation.getFirstDate());
    assertEquals(expectedLastDate, vacation.getLastDate());
    assertEquals(15.0, vacation.getDays());
    assertEquals(expectedStatus, vacation.getStatus());

  }

}
