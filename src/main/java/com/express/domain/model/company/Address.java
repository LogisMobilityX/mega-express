package com.express.domain.model.company;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class Address {

    private final String city;
    private final PostalCode postalCode;
    private final String streetAddress;


}
