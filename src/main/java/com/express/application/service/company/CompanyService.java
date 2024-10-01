package com.express.application.service.company;

import com.express.application.port.input.company.CompanyUseCase;
import com.express.application.port.input.company.ModifyCompanyCommand;
import com.express.application.port.input.company.ReadCompanyQuery;
import com.express.application.port.input.company.RegisterCompanyCommand;
import com.express.application.port.output.company.CompanyProcessor;
import com.express.application.port.output.company.CompanyReader;
import com.express.application.port.output.file.FileUploader;
import com.express.application.port.output.messaging.MessagePublisher;
import com.express.domain.model.company.Company;
import com.express.domain.model.company.CompanyCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService implements CompanyUseCase {

    private final CompanyReader companyReader;
    private final CompanyProcessor companyProcessor;
    private final MessagePublisher messagePublisher;
    private final FileUploader fileUploader;


    @Override
    public Company readCompany(ReadCompanyQuery query) {
        return null;
    }

    @Override
    public boolean registerCompany(RegisterCompanyCommand command) {
        Company company = Company.registerBy(command);

        String filePath = fileUploader.upload(company.getBusinessNumberFile());
        companyProcessor.register(company);

        messagePublisher.publish(company.listEvents());
        company.clearEvents();
        return false;
    }

    @Override
    public boolean modifyCompany(ModifyCompanyCommand command) {
        return false;
    }


}
