package com.express.application.port.input.company;

import com.express.domain.model.company.Address;
import com.express.domain.model.company.BusinessNumber;
import com.express.domain.model.company.BusinessNumberFile;
import com.express.domain.model.company.CompanyCategory;
import com.express.domain.model.company.CompanyCode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
public record RegisterCompanyCommand(
    Long ownerId,
    String ownerName,
    CompanyCategory companyCategory,
    LocalDate companyStartDate,
    Address address,
    String companyName,
    BusinessNumber businessNumber,
    BusinessNumberFile businessNumberFile
) {


}