package com.express.domain.model.company;

import com.express.application.port.input.company.RegisterCompanyCommand;
import com.express.domain.model.EventBase;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class Company extends EventBase {

    private final Owner owner;
    private final String companyName;

    private final CompanyCategory companyCategory;
    private final LocalDateTime companyStartDate;

    private final CompanyCode companyCode;
    private final BusinessNumber businessNumber;
    private final MultipartFile businessNumberFile;
    private final Status status;

    public static Company registerBy(RegisterCompanyCommand command){
        return Company.builder()
            .build();
    }

    @Builder
    private Company(
        Owner owner, String companyName, CompanyCategory companyCategory, LocalDateTime companyStartDate, CompanyCode companyCode,  BusinessNumber businessNumber,
        MultipartFile businessNumberFile, Status status) {
        this.owner = owner;
        this.companyName = companyName;
        this.companyCategory = companyCategory;
        this.companyStartDate = companyStartDate;
        this.companyCode = companyCode;
        this.businessNumber = businessNumber;
        this.businessNumberFile = businessNumberFile;
        this.status = status;
    }
}
