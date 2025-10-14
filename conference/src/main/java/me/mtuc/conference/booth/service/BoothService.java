package me.mtuc.conference.booth.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.booth.dto.BoothEditResponseDto;
import me.mtuc.conference.booth.dto.BoothInfoDto;
import me.mtuc.conference.booth.dto.BoothRequestDto;
import me.mtuc.conference.booth.entity.Booth;
import me.mtuc.conference.booth.entity.Staff;
import me.mtuc.conference.booth.repository.BoothRepository;
import me.mtuc.conference.common.entity.FeeItem;
import me.mtuc.conference.common.repository.FeeItemsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

    public Long newBooth(BoothRequestDto boothRequestDto) {
        BoothInfoDto boothInfo = boothRequestDto.getBoothInfo();

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

        boothRequestDto.getStaffs().forEach(staffInfoDto -> {
            Staff staff = Staff.builder()
                    .name(staffInfoDto.getName())
                    .affiliation(staffInfoDto.getAffiliation())
                    .position(staffInfoDto.getPosition())
                    .build();
            booth.addStaff(staff);
        });

        Booth savedBooth = boothRepository.save(booth);
        return savedBooth.getId();
    }

    public Long editBooth(Long id, BoothRequestDto boothRequestDto) {
        Booth booth = boothRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("부스 신청 내역이 없습니다."));

        boothRepository.deleteStaffNativeQuery(booth.getId());

        FeeItem feeItem = feeItemsRepository.findById(boothRequestDto.getBoothInfo().getFeeItemId()).orElseThrow(() -> new IllegalArgumentException("해당 금액이 없습니다"));
        booth.updateBooth(boothRequestDto, feeItem);

        return id;
    }
}
