package com.bcaliskan.redissondemo.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Long id;
    private String externalId;
    private String name;

}