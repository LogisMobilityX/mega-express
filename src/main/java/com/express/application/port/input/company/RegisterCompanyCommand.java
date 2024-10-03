package com.express.application.port.input.company;

import com.express.domain.model.company.BusinessNumber;
import com.express.domain.model.company.BusinessNumberFile;
import com.express.domain.model.company.CompanyCategory;
import com.express.domain.model.company.CompanyCode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public record RegisterCompanyCommand(String companyName,
                                     BusinessNumber businessNumber,
                                     BusinessNumberFile businessNumberFile,
                                     CompanyCategory companyCategory,
                                     LocalDate companyStartDate) {



}