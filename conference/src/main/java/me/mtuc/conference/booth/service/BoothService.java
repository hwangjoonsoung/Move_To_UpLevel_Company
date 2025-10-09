package me.mtuc.conference.booth.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.booth.dto.BoothEditResponseDto;
import me.mtuc.conference.booth.entity.Booths;
import me.mtuc.conference.booth.repository.BoothRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoothService {

    private final BoothRepository boothRepository;

    public BoothEditResponseDto findBoothById(Long id) {
        Booths booth = boothRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 부스 정보가 없습니다"));

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

}
