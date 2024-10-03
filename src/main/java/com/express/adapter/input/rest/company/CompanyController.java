package com.express.adapter.input.rest.company;

import com.express.adapter.input.rest.common.CustomResponse;
import com.express.adapter.input.rest.company.model.CompanyCreateModel;
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
    public ResponseEntity<CustomResponse> createCompany(@RequestBody CompanyCreateModel model){
        //companyUseCase.registerCompany(model) //model -> command mapper 추가

        return null;
    }
}
