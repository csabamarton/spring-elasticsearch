package com.csmarton.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "user")
public class User {

   @Id
   private String uuid;

   @Field(type = FieldType.Text, name = "firstname")
   private String firstName;

   @Field(type = FieldType.Text, name = "lastname")
   private String lastName;

   @Field(type = FieldType.Text, name = "email")
   private String email;
}
