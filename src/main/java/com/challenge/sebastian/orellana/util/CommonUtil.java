package com.challenge.sebastian.orellana.util;

import com.challenge.sebastian.orellana.dto.PhoneDTO;
import com.challenge.sebastian.orellana.dto.UserDTO;
import com.challenge.sebastian.orellana.entity.Phone;
import com.challenge.sebastian.orellana.entity.User;
import com.challenge.sebastian.orellana.exception.InvalidArgumentException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static UserDTO mapperUserEntityToUserDto(User user, List<PhoneDTO> usersPhones) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(usersPhones)
                .active(user.isActive())
                .build();
    }

    public static PhoneDTO mapperPhoneEntityToPhoneDto(Phone phone) {
        return PhoneDTO.builder()
                .number(phone.getNumber())
                .countryCode(phone.getCountryCode())
                .cityCode(phone.getCityCode())
                .build();
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean isNotNullAndNotEmpty(String cadena) {
        return cadena != null && !cadena.trim().equals("");
    }

    public static void passCheck(String password) {
        int length = password.length();
        if (length < 2) throw new IllegalArgumentException("!the password is too short!");

        if (password.replaceAll("[A-Za-z0-9]", "").length() > 1)
            throw new InvalidArgumentException("!Password contains invalid characters!");

        boolean numberLength = password.replaceAll("[^0-9]", "").length() == 2;
        boolean uppercaseLength = password.replaceAll("[^A-Z]", "").length() == 1;

        if (!numberLength)
            throw new InvalidArgumentException("¡The password must contain at least two numbers!");
        else if (!uppercaseLength)
            throw new InvalidArgumentException("¡The password must contain at least one numbers!");
    }


    public static void validatedRequest(UserDTO userDto) {
        boolean isRequestValid = true;
        String error = "";
        if (!isNotNullAndNotEmpty(userDto.getName())) {
            error = error + "Invalid user name;";
            isRequestValid = false;
        }
        passCheck(userDto.getPassword());
        if (!validate(userDto.getEmail())) {
            error = error + "Invalid email;";
            isRequestValid = false;
        }
        if (!isRequestValid) {
            throw new InvalidArgumentException(error);
        }
    }
}
