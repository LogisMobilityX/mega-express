package com.express.adapter.input.rest.user.response;

import com.express.adapter.common.security.TokenInfo;
import com.express.domain.model.user.Email;
import com.express.domain.model.user.User;
import com.express.domain.model.user.UserGrade;
import com.express.domain.model.user.UserName;
import lombok.Builder;
import lombok.Getter;
@Getter

public class AuthenticatedResponse {
    private TokenInfo tokenInfo;
    private AuthenticatedUserResponse authenticatedUserResponse;
    @Builder
    public AuthenticatedResponse(TokenInfo tokenInfo, User user) {
        this.tokenInfo = tokenInfo;
        this.authenticatedUserResponse = AuthenticatedUserResponse.builder()
                .user(user)
                .build();

    }

    @Getter
    public static class AuthenticatedUserResponse{
        private String email;
        private String userName;
        private UserGrade userGrade;
        @Builder
        public AuthenticatedUserResponse(User user) {
            this.email = user.getEmail().getEmailText();
            this.userName = user.getUserName().getUserNameText();
            this.userGrade = user.getUserGrade();
        }
    }
}