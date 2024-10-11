package com.express.adapter.input.rest.company.response;


import com.express.application.port.input.company.RegisterCompanyCommand;
import com.express.domain.model.company.BusinessNumber;
import com.express.domain.model.company.BusinessNumberFile;
import com.express.domain.model.company.Company;
import com.express.domain.model.company.CompanyCategory;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Getter
@NoArgsConstructor
public class CompanyReadResponse {
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
    private CompanyReadResponse(String businessNumber, String businessNumberFilePath,
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

    public static CompanyReadResponse from(Company company){
        return CompanyReadResponse.builder()
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
