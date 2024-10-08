package com.express.application.port.input.company;

import com.express.domain.model.company.Company;

public interface CompanyUseCase {

    Company readCompany(ReadCompanyQuery query);

    boolean registerCompany(RegisterCompanyCommand command);

    boolean modifyCompany(ModifyCompanyCommand command);
}
