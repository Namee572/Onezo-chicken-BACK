package com.green.onezo.menu;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Menu-Controller", description = "메뉴")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/list")
    public ResponseEntity<List<Menu>> list(){
        List<Menu> menuList = menuService.allMenuView();
        return ResponseEntity.status(HttpStatus.OK).body(menuList);
    }

}
