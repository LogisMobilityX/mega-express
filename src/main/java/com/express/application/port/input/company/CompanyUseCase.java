package com.express.application.port.input.company;

import com.express.domain.model.company.Company;
import com.express.domain.model.company.CompanyCode;

public interface CompanyUseCase {

    Company readCompany(ReadCompanyQuery query);

    boolean registerCompany(RegisterCompanyCommand command);

    boolean modifyCompany(ModifyCompanyCommand command);

}
