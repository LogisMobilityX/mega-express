package com.express.adapter.input.rest.user.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReissueAccessTokenResponse {
    private String accessToken;

    @Builder
    public ReissueAccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public static ReissueAccessTokenResponse reissueAccessTokenResponse(String token){
        return ReissueAccessTokenResponse.builder()
                .accessToken(token)
                .build();
    }
}
