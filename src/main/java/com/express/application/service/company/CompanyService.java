package com.express.application.service.company;

import com.express.application.exception.company.CompanyNotFoundException;
import com.express.application.port.input.company.CompanyUseCase;
import com.express.application.port.input.company.ModifyCompanyCommand;
import com.express.application.port.input.company.ReadCompanyQuery;
import com.express.application.port.input.company.RegisterCompanyCommand;
import com.express.application.port.output.company.CompanyProcessor;
import com.express.application.port.output.company.CompanyReader;
import com.express.application.port.output.file.FileUploader;
import com.express.application.port.output.messaging.MessagePublisher;
import com.express.domain.model.company.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        return companyReader.readCompany(query).orElseThrow(CompanyNotFoundException::new);
    }

    @Transactional
    @Override
    public Company registerCompany(RegisterCompanyCommand command, Long userId) {

        companyReader.readByBusinessNumber(command.businessNumber())
            .orElseThrow(() -> new RuntimeException("Erro"));

        String businessNumberFilePath = fileUploader.upload(command.businessNumberFile().getFile());
        command.businessNumberFile().uploadComplete(businessNumberFilePath);

        Company newCompany = Company.register(command);
        companyProcessor.register(newCompany);

        messagePublisher.publish(newCompany.listEvents());
        newCompany.clearEvents();

        return newCompany;
    }

    @Override
    public boolean modifyCompany(ModifyCompanyCommand command) {
        return false;
    }


}
