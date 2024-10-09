package com.express.application.port.output.user;

import java.util.Map;

public interface UserAuthProcessor {
    Map<String,Object> addAuthorization(String email, String token, Map<String,Object> authorization);
    Map<String,Object> deleteAuthorization(String email, String token, Map<String,Object> authorization);
}
