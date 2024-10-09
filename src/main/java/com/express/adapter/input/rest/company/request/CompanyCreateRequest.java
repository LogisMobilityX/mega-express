package com.express.adapter.input.rest.company.request;


import com.express.application.port.input.company.RegisterCompanyCommand;
import com.express.domain.model.company.BusinessNumber;
import com.express.domain.model.company.BusinessNumberFile;
import com.express.domain.model.company.CompanyCategory;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateRequest {

    private String companyName;
    private LocalDate companyStartDate;
    private String companyCategory;
    private String businessNumber;
    private MultipartFile businessNumberFile;

    public RegisterCompanyCommand toCommand(){
//        return null;
        return RegisterCompanyCommand
            .builder()
            .companyName(companyName)
            .businessNumber(BusinessNumber.from(businessNumber))
            .businessNumberFile(BusinessNumberFile.from(businessNumberFile))
            .companyCategory(CompanyCategory.from(companyCategory))
            .companyStartDate(companyStartDate)
            .build();
    }
}
