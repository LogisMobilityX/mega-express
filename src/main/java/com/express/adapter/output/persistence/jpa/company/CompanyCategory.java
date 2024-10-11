package com.express.adapter.output.persistence.jpa.company;

public enum CompanyCategory {

    INANCE,
    TECHNOLOGY,
    MANUFACTURING,
    HEALTHCARE,
    RETAIL,
    LOGISTICS,
    EDUCATION,
    HOSPITALITY,
    ENTERTAINMENT,
    CONSTRUCTION,
    ENERGY,
    TELECOMMUNICATION,
    AGRICULTURE,
    GOVERNMENT,
    NON_PROFIT,
    AUTOMOTIVE,
    FOOD_BEVERAGE,
    REAL_ESTATE,
    CONSULTING,
    OTHER;


    public static CompanyCategory fromString(String category) {
        for (CompanyCategory companyCategory : CompanyCategory.values()) {
            if (companyCategory.name().equalsIgnoreCase(category)) {
                return companyCategory;
            }
        }
        return CompanyCategory.OTHER;
    }
}
