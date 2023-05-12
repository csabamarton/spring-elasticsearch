package com.csmarton.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class CreateUserRequest {

   @NotBlank(message = "First Name cannot be empty")
   @NotNull(message = "First Name is required")
   private String firstName;

   @NotBlank(message = "Last Name cannot be empty")
   @NotNull(message = "Last Name is required")
   private String lastName;


   @NotBlank(message = "Email cannot be empty")
   @NotNull(message = "Email Name is required")
   @Email(message = "Invalid email")
   private String email;
}
