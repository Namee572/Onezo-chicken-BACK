package com.green.onezo.menu;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuInfoRepository menuInfoRepository;
    private final NutrientRepository nutrientRepository;



//    public Optional<MenuDetailDto> getAllMenuDetails(Long menuId) {
//        Optional<Menu> menu = menuRepository.findById(menuId);
//        if (!menu.isPresent()) return Optional.empty();
//
//        List<MenuInfo> menuInfos = menuInfoRepository.findAllByMenuId(menu.get().getId());
//        List<Nutrient> nutrients = nutrientRepository.findAllByMenuId(menu.get().getId());
//
//        return Optional.of(new MenuDetailDto(menu.get(),menuInfos,nutrients));
//    }

//    //아이디 값으로 메뉴 리스트
//    public Optional<Menu> menuId(Long id){
//        return Optional.of(menuRepository.findById(id).get());
//    }
    public MenuDetailDto getAllMenuDetails(Long menuId) {
        ModelMapper modelMapper = new ModelMapper();

        Optional<Menu> menuOpt = menuRepository.findById(menuId);
        if (menuOpt.isPresent()) {
            return modelMapper.map(menuOpt, MenuDetailDto.class);
        }
        Menu menu = menuOpt.get();
        List<MenuInfo> menuInfos = menuInfoRepository.findAllByMenuId(menuId);
        List<Nutrient> nutrients = nutrientRepository.findAllByMenuId(menuId);

        return null;
    }

//    //아이디값으로 영양정보
//    public Optional<MenuInfo> menuInfoId(Long id){
//        return Optional.of(menuInfoRepository.findById(id).get());
//    }
//    //아이디값으로 칼로리정보
//    public Optional<Nutrient> nutrient(Long id){
//        return Optional.of(nutrientRepository.findById(id).get());
//    }
//
//    //영양 정보
//    public List<MenuInfo> menuInfos() {
//        return menuInfoRepository.findAll();
//    }
//    //칼로리 정보
//    public List<Nutrient> nutrients(){
//        return nutrientRepository.findAll();
//    }

}