package com.express.adapter.output.persistence.jpa.company;

import com.express.domain.model.company.Company;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "company")
public class CompanyJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_code")
    private String companyCode;

    @Column(name = "business_number_path")
    private String businessNumberPath;

    @Column(name = "business_number")
    private String businessNumber;

    @Column(name = "company_start_date")
    private LocalDate companyStartDate;

    @Column(name = "company_category")
    private CompanyCategory companyCategory;

    @Builder
    private CompanyJpaEntity(Long ownerId, String ownerName, String companyName,
        String companyCode, String businessNumberPath, String businessNumber,
        LocalDate companyStartDate, CompanyCategory companyCategory) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.companyName = companyName;
        this.companyCode = companyCode;
        this.businessNumberPath = businessNumberPath;
        this.businessNumber = businessNumber;
        this.companyStartDate = companyStartDate;
        this.companyCategory = companyCategory;
    }

    public static CompanyJpaEntity from(Company company){
        return CompanyJpaEntity.builder()
            .ownerId(company.getOwnerId())
            .ownerName(company.getOwnerName())
            .companyCode(company.getCompanyCode().getCode())
            .companyName(company.getCompanyName())
            .businessNumberPath(company.getBusinessNumberFile().getFilePath())
            .businessNumber(company.getBusinessNumber().getNumber())
            .companyStartDate(company.getCompanyStartDate())
            .companyCategory(CompanyCategory.fromString(company.getCompanyCategory().name()))
            .build();
    }
}
