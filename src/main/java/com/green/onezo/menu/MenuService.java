package com.green.onezo.menu;

import com.green.onezo.store.OrderType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuInfoRepository menuInfoRepository;
    private final NutrientRepository nutrientRepository;

    //아이디 값으로 메뉴 리스트
    public Optional<Menu> menuId(Long id){
        return Optional.of(menuRepository.findById(id).get());
    }


    //아이디값으로 영양정보
    public Optional<MenuInfo> menuInfoId(Long id){
        return Optional.of(menuInfoRepository.findById(id).get());
    }
    //아이디값으로 칼로리정보
    public Optional<Nutrient> nutrient(Long id){
        return Optional.of(nutrientRepository.findById(id).get());
    }
    //카테고리 별 메뉴 조회

    @Transactional
    public List<MenuCategory> menuCategories(MenuCategory menuCategory){
        List<MenuCategory> menuCategories = new ArrayList<>();
        menuCategories.add(MenuCategory.SET);
        menuCategories.add(MenuCategory.CHICKEN);
        menuCategories.add(MenuCategory.SIDE);
        menuCategories.add(MenuCategory.DRINK);
        menuCategories.add(MenuCategory.SAUCE);
        return menuCategories;
    }
    //영양 정보
    public List<MenuInfo> menuInfos() {
        return menuInfoRepository.findAll();
    }
    //칼로리 정보
    public List<Nutrient> nutrients(){
        return nutrientRepository.findAll();
    }

}