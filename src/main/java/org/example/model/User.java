package org.example.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(unique = true)
   private String name;

   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column
   private Integer ago;

   @Column
   private String password;


   @OneToMany(mappedBy="user", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
   private Set<Role> roles;



   public User() {}
   
   public User(String name, String password, String firstName, String lastName, Integer ago) {
      this.name = name;
      this.password = password;
      this.lastName = lastName;
      this.firstName = firstName;
      this.ago = ago;
   }




   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return roles;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return name;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }




   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public Integer getAgo() {
      return ago;
   }

   public void setAgo(Integer ago) {
      this.ago = ago;
   }


   public void setPassword(String password) {
      this.password = password;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Name: ").append(name).append(", ")
              .append("Last name: ").append(lastName).append(", ")
              .append("First name: ").append(firstName).append(", ")
              .append("Ago: ").append(ago).append(", ")
              .append("Roles: ").append(roles).append('\n');

      return sb.toString();
   }

}
