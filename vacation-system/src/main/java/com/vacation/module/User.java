package com.vacation.module;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails, Principal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String FirstName;

  private String LastName;

  private String userName;

  private String password;

  @OneToMany(mappedBy = "requestedBy", cascade = CascadeType.ALL)
  private List<Vacation> vacations = new ArrayList<>();

  @OneToMany(mappedBy = "approvedByM")
  private List<Vacation> approvedVacationsM = new ArrayList<>();

  @OneToMany(mappedBy = "approvedByD")
  private List<Vacation> approvedVacationsD = new ArrayList<>();


  @ManyToMany(fetch = FetchType.EAGER)
  private List<Role> roles;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

  @Override
  public String getName() {
    return userName;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // return this.roles.stream()
    //     .map(r -> new SimpleGrantedAuthority(r.getName()))
    //     .collect(Collectors.toList());
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

}
