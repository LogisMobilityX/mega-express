package com.express.domain.model.company;

import com.express.application.port.input.company.RegisterCompanyCommand;
import com.express.domain.model.EventBase;
import com.express.domain.model.company.event.CompanyRegisterEvent;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Company extends EventBase {

    private final Owner owner;
    private final String companyName;

    private final CompanyCategory companyCategory;
    private final LocalDate companyStartDate;

    private final CompanyCode companyCode;
    private final BusinessNumber businessNumber;
    private final BusinessNumberFile businessNumberFile;
    private final RegisterStatus registerStatus;

    @Builder
    private Company(
        Owner owner, String companyName, CompanyCategory companyCategory, LocalDate companyStartDate, CompanyCode companyCode,  BusinessNumber businessNumber,
        BusinessNumberFile businessNumberFile, RegisterStatus registerStatus) {
        this.owner = owner;
        this.companyName = companyName;
        this.companyCategory = companyCategory;
        this.companyStartDate = companyStartDate;
        this.companyCode = companyCode;
        this.businessNumber = businessNumber;
        this.businessNumberFile = businessNumberFile;
        this.registerStatus = registerStatus;
    }

    public static Company register(RegisterCompanyCommand command) {
        Company newCompany = Company.builder()
            .companyName(command.companyName())
            .companyCategory(command.companyCategory())
            .businessNumberFile(command.businessNumberFile())
            .businessNumber(command.businessNumber())
            .companyStartDate(command.companyStartDate())
            .build();

        newCompany.getDomainEventList().add(new CompanyRegisterEvent());
        return newCompany;
    }
}
