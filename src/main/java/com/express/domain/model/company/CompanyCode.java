package com.express.domain.model.company;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class CompanyCode {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    private final String code;

    public static CompanyCode generate(){
        return new CompanyCode(generateCode());
    }
    private CompanyCode(String code) {
        this.code = code;
    }

    private static String generateCode(){
        return random.ints(6, 0, ALPHA_NUMERIC_STRING.length())
            .mapToObj(ALPHA_NUMERIC_STRING::charAt)
            .map(Object::toString)
            .collect(Collectors.joining());
    }

}
