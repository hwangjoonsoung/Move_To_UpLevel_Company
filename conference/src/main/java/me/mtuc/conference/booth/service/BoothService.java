package me.mtuc.conference.booth.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.booth.entity.Booths;
import me.mtuc.conference.booth.repository.BoothRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoothService {

    private final BoothRepository boothRepository;

    public Booths findBoothById(Long id) {
        Booths booth = boothRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 부스 정보가 없습니다"));
        // todo: 여기서 boothEditResponseDto를 만드어서 보내야 함.

        return booth;
    }

}
