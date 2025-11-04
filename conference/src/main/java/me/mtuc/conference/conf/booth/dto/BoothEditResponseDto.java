package me.mtuc.conference.conf.booth.dto;

public record BoothEditResponseDto(
        String companyName
        , String ceoName
        , String companyPhoneNumber
        , int boothCount
        , String boothIds
        , String managerAffiliations
        , String managerPhoneNumber
        , String managerEmail
) {
}
