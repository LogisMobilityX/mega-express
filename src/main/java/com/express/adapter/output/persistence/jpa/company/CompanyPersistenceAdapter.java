//package com.express.adapter.output.persistence.jpa.company;
//
//import com.express.application.port.input.company.RegisterCompanyCommand;
//import com.express.application.port.output.company.CompanyProcessor;
//import com.express.application.port.output.company.CompanyReader;
//import com.express.domain.model.company.BusinessNumber;
//import com.express.domain.model.company.Company;
//import java.util.Optional;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public class CompanyPersistenceAdapter implements CompanyReader, CompanyProcessor {
//
//    private final CompanyJpaRepository companyJpaRepository;
//
//    @Override
//    public void modify(Company company) {
//
//    }
//
//    @Override
//    public void register(Company company) {
//
//    }
//
//
////    @Override
////    public Optional<Company> readById(String id) {
////        return Optional.empty();
////    }
//
//    @Override
//    public Optional<Company> readByBusinessNumber(BusinessNumber businessNumber) {
//        return Optional.empty();
//    }
//}
