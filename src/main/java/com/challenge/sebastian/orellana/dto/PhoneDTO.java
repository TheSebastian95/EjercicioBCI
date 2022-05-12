package com.challenge.sebastian.orellana.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneDTO {
    private int number;
    private int cityCode;
    private int countryCode;
}
