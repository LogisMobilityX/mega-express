package com.express.domain.model.company;

import com.express.application.port.input.company.ModifyCompanyCommand;
import com.express.application.port.input.company.RegisterCompanyCommand;
import com.express.domain.model.EventBase;
import com.express.domain.model.company.event.CompanyModifyEvent;
import com.express.domain.model.company.event.CompanyRegisterEvent;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Company extends EventBase {

    private final Owner owner;

    private final Long companyId;
    private String companyName;

    private CompanyCategory companyCategory;
    private LocalDate companyStartDate;

    private CompanyCode companyCode;
    private BusinessNumber businessNumber;
    private BusinessNumberFile businessNumberFile;

    @Builder
    private Company(
        Owner owner, Long companyId, String companyName, CompanyCategory companyCategory, LocalDate companyStartDate, CompanyCode companyCode,  BusinessNumber businessNumber,
        BusinessNumberFile businessNumberFile) {
        this.owner = owner;
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyCategory = companyCategory;
        this.companyStartDate = companyStartDate;
        this.companyCode = companyCode;
        this.businessNumber = businessNumber;
        this.businessNumberFile = businessNumberFile;
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

    public Company update(ModifyCompanyCommand command) {

        this.companyName = command.companyName();
        this.companyCategory = command.companyCategory();
        this.businessNumber = command.businessNumber();
        this.businessNumberFile = command.businessNumberFile();
        this.companyStartDate = command.companyStartDate();

        this.getDomainEventList().add(new CompanyModifyEvent());

        return this;
    }

}
