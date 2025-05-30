package com.vacation.module;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.vacation.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vacation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private Date addedDate;

  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(nullable = false, updatable = false)
  private Date firstDate;

  @Column(nullable = false, updatable = false)
  private Date lastDate;

  private Double days;

  @ManyToOne
  @JoinColumn(name = "requested_by_id")
  private User requestedBy;

  @ManyToOne
  @JoinColumn(name = "approved_byM_id")
  private User approvedByM;

  @ManyToOne
  @JoinColumn(name = "approved_byD_id")
  private User approvedByD;

}
