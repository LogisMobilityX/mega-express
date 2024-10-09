package com.express.application.port.input.company;

import com.express.domain.model.company.BusinessNumber;
import com.express.domain.model.company.BusinessNumberFile;
import com.express.domain.model.company.CompanyCategory;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record ModifyCompanyCommand(Long companyId,
                                   String companyName,
                                   BusinessNumber businessNumber,
                                   BusinessNumberFile businessNumberFile,
                                   CompanyCategory companyCategory,
                                   LocalDate companyStartDate) {


}