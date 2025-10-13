package me.mtuc.conference.booth.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.booth.dto.BoothEditResponseDto;
import me.mtuc.conference.booth.dto.BoothInfoDto;
import me.mtuc.conference.booth.dto.BoothNewRequestDto;
import me.mtuc.conference.booth.entity.Booth;
import me.mtuc.conference.booth.entity.Staff;
import me.mtuc.conference.booth.repository.BoothRepository;
import me.mtuc.conference.common.repository.FeeItemsRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoothService {

    private final BoothRepository boothRepository;
    private final FeeItemsRepository feeItemsRepository;

    public BoothEditResponseDto findBoothById(Long id) {
        Booth booth = boothRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 부스 정보가 없습니다"));

        BoothEditResponseDto boothEditResponseDto = new BoothEditResponseDto(booth.getCompanyName(),
                booth.getCeoName(),
                booth.getCompanyPhoneNumber(),
                booth.getBoothCount(),
                booth.getBoothIds(),
                booth.getManagerAffiliations(),
                booth.getManagerPhoneNumber(),
                booth.getManagerEmail());
        return boothEditResponseDto;
    }

    public void newBooth(BoothNewRequestDto boothNewRequestDto) {
        BoothInfoDto boothInfo = boothNewRequestDto.getBoothInfo();

        Booth booth = Booth.builder()
                .companyName(boothInfo.getCompanyName())
                .ceoName(boothInfo.getCeoName())
                .companyPhoneNumber(boothInfo.getCompanyPhoneNumber())
                .boothCount(boothInfo.getBoothCount())
                .boothIds(boothInfo.getBoothIds())
                .managerName(boothInfo.getManagerName())
                .managerAffiliations(boothInfo.getManagerAffiliations())
                .managerPhoneNumber(boothInfo.getManagerPhoneNumber())
                .managerEmail(boothInfo.getManagerEmail())
                .password(boothInfo.getPassword())
                .price(boothInfo.getPrice())
                .feeItem(feeItemsRepository.getReferenceById(boothInfo.getFeeItemId()))
                .build();

        boothNewRequestDto.getStaffs().forEach(staffInfoDto -> {
            Staff staff = Staff.builder()
                    .name(staffInfoDto.getName())
                    .affiliation(staffInfoDto.getAffiliation())
                    .position(staffInfoDto.getPosition())
                    .build();
            booth.addStaff(staff);
        });

        boothRepository.save(booth);
    }

}
