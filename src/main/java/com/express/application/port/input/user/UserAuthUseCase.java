package com.express.application.port.input.user;

import java.util.Map;

public interface UserAuthUseCase {



    Map<String,Object> login();

    String logOut();
}
