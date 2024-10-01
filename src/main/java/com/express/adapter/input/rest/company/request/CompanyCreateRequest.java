package com.express.adapter.input.rest.company.request;


import com.express.application.port.input.company.RegisterCompanyCommand;

public class CompanyCreateRequest {


    public static RegisterCompanyCommand toCommand(){
        return RegisterCompanyCommand
            .builder().build();
    }
}
