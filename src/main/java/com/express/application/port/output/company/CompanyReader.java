package com.express.application.port.output.company;

import com.express.domain.model.company.Company;
import java.util.Optional;

public interface CompanyReader {

    Optional<Company> readById(String id);
}
