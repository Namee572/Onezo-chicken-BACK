package com.green.onezo.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

//    public Menu menuView(Long id) {
//        return menuRepository.findById(id).get();
//    }
    public List<Menu> allMenuView() {
        return menuRepository.findAll();
    }


    public Optional<Menu> menuAbout(String origin,String nutrient){
        return menuRepository.findByOriginAndNutrient(origin, nutrient);
    }
    //아이디 값으로 영양 정보
    public Optional<Menu> findById(Long id) {
        return Optional.of(menuRepository.findById(id).get());
    }
}
