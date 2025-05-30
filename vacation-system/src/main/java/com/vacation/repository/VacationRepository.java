package com.vacation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vacation.module.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

  @Query(
      """
      SELECT vacations FROM Vacation vacations
      WHERE vacations.requestedBy.id = :userId
      """
  )
  List<Vacation> findAllByIdNoTeamId(Long userId);

  @Query(
      """
      SELECT vacations FROM Vacation vacations
      WHERE vacations.requestedBy.id = :userId AND vacations.requestedBy.team.id = :teamId
      """)
  List<Vacation> getVacationsByUserIdTeamSelected(Long teamId, Long userId);

  @Query(
      """
      SELECT v FROM Vacation v WHERE v.requestedBy.team.id = :teamId
      """
  )
  List<Vacation> findByTeamId(Long teamId);

}
