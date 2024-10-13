package com.express.application.service.user;


import com.express.adapter.common.UseCase;
import com.express.adapter.input.rest.user.response.ReadUserResponse;
import com.express.application.port.input.user.*;
import com.express.application.port.output.user.UserProcessor;
import com.express.application.port.output.user.UserReader;
import com.express.domain.model.user.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * 유저 생성
 * 유저 정보 수정
 * 유저 탈퇴
 * 유저 조회
 * 로그인
 * 로그아웃
 *
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UserService implements UserProcessorUseCase,
        UserReadUseCase{
    //유저 관련
    private final UserProcessor userProcessor;
    private final UserReader userReader;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void joinUser(JoinUserCommand joinUserRequest) {
        //user 객체로 변환
        User user = User.builder()
                .userId(UserId.from(0L))
                .email(Email.from(joinUserRequest.getEmail().getEmailText()))
                .userName(UserName.from(joinUserRequest.getUsername().getUserNameText()))
                .userGrade(
                        joinUserRequest.getUserGrade()
                )
                .password(
                        Password.from(
                                passwordEncoder.encode(joinUserRequest.getPassword().getPasswordText())
                        )
                )
                .phoneNumber(PhoneNumber.from(joinUserRequest.getPhoneNumber().getPhoneNumberText()))
                .build();
        userProcessor.saveUser(user);
    }

    @Override
    @Transactional
    public void modifyUserInfo(Long userId, ModifyUserCommand modifyUserCommand) {
        //
        //user 객체로 변환
        User user = User.builder()
                .userId(UserId.from(0L))
                .email(Email.from(modifyUserCommand.getEmail().getEmailText()))
                .userName(UserName.from(modifyUserCommand.getUsername().getUserNameText()))
                .userGrade(modifyUserCommand.getUserGrade())
                .phoneNumber(PhoneNumber.from(modifyUserCommand.getPhoneNumber().getPhoneNumberText()))
                .build();
        userProcessor.modifyUserInfo(userId, user);
    }

    @Override
    @Transactional
    public boolean withdrawalUser(Long userId, WithdrawalUserCommand withdrawalUserCommand) {
        //id로 유저 정보 조회
        User user = userReader.UserInfoById(userId);
        //패스워드 검증
        String inputPassword = withdrawalUserCommand.getPassword().getPasswordText();
        if (user.getPassword().comparePassword(inputPassword)){
            // 삭제
            userProcessor.withdrawalUserById(userId);
            return true;
        }else{
            log.info("user withdrawal failed");
            throw new RuntimeException();
        }
    }


    @Override
    public ReadUserResponse findByEmail(String email) {
        return null;
    }

    @Override
    public ReadUserResponse findById(Long userId) {
        User user = userReader.UserInfoById(userId);

        return ReadUserResponse.builder()
                .email(user.getEmail().getEmailText())
                .username(user.getUserName().getUserNameText())
                .userGrade(user.getUserGrade().name())
                .phoneNumber(user.getPhoneNumber().getPhoneNumberText())
                .certifiedEmail(true)
                .build();
    }

}