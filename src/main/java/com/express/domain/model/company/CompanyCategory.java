package com.express.domain.model.company;

public enum CompanyCategory {
    FINANCE("금융업"),
    TECHNOLOGY("기술업"),
    MANUFACTURING("제조업"),
    HEALTHCARE("헬스케어"),
    RETAIL("유통업"),
    LOGISTICS("물류업"),
    EDUCATION("교육업"),
    HOSPITALITY("숙박업"),
    ENTERTAINMENT("엔터테인먼트"),
    CONSTRUCTION("건설업"),
    ENERGY("에너지업"),
    TELECOMMUNICATION("통신업"),
    AGRICULTURE("농업"),
    GOVERNMENT("공공업"),
    NON_PROFIT("비영리업"),
    AUTOMOTIVE("자동차산업"),
    FOOD_BEVERAGE("식음료업"),
    REAL_ESTATE("부동산업"),
    CONSULTING("컨설팅업");

    private final String koreanName;

    CompanyCategory(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }


    public static CompanyCategory from(String value) {
        for (CompanyCategory category : CompanyCategory.values()) {
            if (category.name().equalsIgnoreCase(value) || category.getKoreanName()
                .equals(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("해당하는 회사 분류가 없습니다: " + value);
    }

}