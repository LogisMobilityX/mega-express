package com.express.application.port.input.company;

import com.express.domain.model.company.Company;

public interface CompanyUseCase {

    Company readCompany(ReadCompanyQuery query);

    Company registerCompany(RegisterCompanyCommand command, Long userId);

    Company modifyCompany(ModifyCompanyCommand command);

}
