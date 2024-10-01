package com.express.domain.model.company;

import com.express.application.port.input.company.RegisterCompanyCommand;
import com.express.domain.model.EventBase;
import com.express.infrasturcture.event.DomainEvent;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class Company extends EventBase {

    private final Owner owner;
    private final String companyName;
    private final CompanyCode companyCode;
    private final Address address;
    private final BusinessNumber businessNumber;
    private final MultipartFile businessNumberFile;
    private final Status status;

    public static Company registerBy(RegisterCompanyCommand command){
        return Company.builder()
            .build();
    }

    @Builder
    private Company(List<DomainEvent> domainEventList,
        Owner owner, String companyName, CompanyCode companyCode, Address address, BusinessNumber businessNumber,
        MultipartFile businessNumberFile, Status status) {
        super(domainEventList);
        this.owner = owner;
        this.companyName = companyName;
        this.companyCode = companyCode;
        this.address = address;
        this.businessNumber = businessNumber;
        this.businessNumberFile = businessNumberFile;
        this.status = status;
    }
}
