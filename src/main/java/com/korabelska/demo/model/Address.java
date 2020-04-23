package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    Long id;
    String addressLine;
    String postalCode;

   public static Address create(String addressLine, String postalCode) {
       return new Address(null,addressLine,postalCode);
   }

}
