package com.express.adapter.input.rest.user.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCertificatedResponse {
    private boolean certificationStatus;

    public static UserCertificatedResponse of(boolean certificationStatus) {
        return UserCertificatedResponse.builder().certificationStatus(certificationStatus).build();
    }
}
