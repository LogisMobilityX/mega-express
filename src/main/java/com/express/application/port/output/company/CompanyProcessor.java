package com.express.application.port.output.company;

import com.express.domain.model.company.Company;

public interface CompanyProcessor {

    void modify(Company company);

    void register(Company company);
}
