package com.express.adapter.input.rest.company;

import com.express.adapter.input.rest.company.request.CompanyCreateRequest;
import com.express.adapter.input.rest.responseTemplate.CustomResponse;
import com.express.application.port.input.company.CompanyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyUseCase companyUseCase;


    @PostMapping
    public ResponseEntity<CustomResponse> createCompany(@RequestBody CompanyCreateRequest request){

        companyUseCase.registerCompany(request.toCommand()); //model -> command mapper 추가

        return null;
    }
}
