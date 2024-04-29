package com.green.onezo.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

//    public Optional<Menu> menuAbout(String origin, String nutrient) {
//        return menuRepository.findByOriginAndNutrient(origin, nutrient);
//    }
//
//    //아이디 값으로 영양 정보
//    public Optional<Menu> findById(Long id) {
//        return Optional.of(menuRepository.findById(id).get());
//    }
}