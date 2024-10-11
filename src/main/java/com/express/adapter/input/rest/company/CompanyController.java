package com.express.adapter.input.rest.company;

import com.express.adapter.input.rest.company.request.CompanyCreateRequest;
import com.express.adapter.input.rest.company.response.CompanyCreateResponse;
import com.express.application.port.input.company.CompanyUseCase;
import com.express.domain.model.company.Company;
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
    public ResponseEntity<CompanyCreateResponse> createCompany(@RequestBody CompanyCreateRequest request){

        Long userId = 1L;

        Company registerCompany = companyUseCase.registerCompany(request.toCommand(), userId);


        return ResponseEntity.ok().body(CompanyCreateResponse.from(registerCompany));
    }
}
