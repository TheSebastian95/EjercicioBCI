package com.challenge.sebastian.orellana.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private boolean active;
    private List<PhoneDTO> phones;
}
