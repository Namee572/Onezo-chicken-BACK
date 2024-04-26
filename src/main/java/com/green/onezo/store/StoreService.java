package com.green.onezo.store;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    // 매장상세 조회
    public StoreDto getStoreById(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("스토어아이디?" + storeId));

        return StoreDto.builder()
                .storeName(store.getStoreName())
                .address(store.getAddress())
                .storePhone(store.getStorePhone())
                .build();
    }

    //메장주소 리스트
    public List<StoreDto> findByStoreNameAndAddressAndAddressOld(@NotNull String storeName, @NotNull String address, @NotNull String addressOld) {

        List<Object[]> storeData = storeRepository.findByStoreNameAndAddressAndAddressOld(storeName, address, addressOld);
        return storeData.stream()
                .map(StoreDto::new)
                .collect(Collectors.toList());
    }
}
