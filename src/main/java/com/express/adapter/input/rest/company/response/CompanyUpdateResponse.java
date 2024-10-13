package com.express.adapter.input.rest.company.response;


import com.express.domain.model.company.Company;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CompanyUpdateResponse {
    private Long ownerId;
    private String ownerName;
    private Long companyId;
    private String companyName;
    private LocalDate companyStartDate;
    private String companyCategory;
    private String companyCode;
    private String businessNumber;
    private String businessNumberFilePath;


    @Builder
    private CompanyUpdateResponse(String businessNumber, String businessNumberFilePath,
        String companyCategory, String companyCode, Long companyId, String companyName,
        LocalDate companyStartDate, Long ownerId, String ownerName) {
        this.businessNumber = businessNumber;
        this.businessNumberFilePath = businessNumberFilePath;
        this.companyCategory = companyCategory;
        this.companyCode = companyCode;
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyStartDate = companyStartDate;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
    }

    public static CompanyUpdateResponse from(Company company){
        return CompanyUpdateResponse.builder()
            .ownerId(company.getOwnerId())
            .ownerName(company.getOwnerName())
            .companyId(company.getCompanyId())
            .companyName(company.getCompanyName())
            .companyStartDate(company.getCompanyStartDate())
            .companyCategory(company.getCompanyCategory().getKoreanName())
            .companyCode(company.getCompanyCode().getCode())
            .businessNumber(company.getBusinessNumber().getNumber())
            .businessNumberFilePath(company.getBusinessNumberFile().getFilePath())
            .build();
    }
}
