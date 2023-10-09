//package com.github.vieiracamargo;
//
//import io.quarkus.runtime.Startup;
//import jakarta.annotation.PostConstruct;
//import jakarta.inject.Inject;
//import jakarta.inject.Singleton;
//
//@Startup
//@Singleton
//public class Execute {
//
//    @Inject
//    CompanyService companyService;
//
//    @PostConstruct
//    public void init(){
//        CnpjNumber cnpjNumber = new CnpjNumber("66261814000198");
//        Address address = new Address(
//                "Dos matagais",
//                "Goiânia",
//                "Goiás",
//                "Brasil",
//                "74597010",
//                "Residencial dos Ipês",
//                "Qd.13 Lt.34"
//        );
//
//        Company company = new Company(
//                "Bruno IT",
//                cnpjNumber,
//                address,
//                "(95)97546-8754",
//                10,
//                20
//        );
//
//        save(company);
//
//    }
//
//    public void save(Company company){
//        companyService.registerCompany(company);
//    }
//}
