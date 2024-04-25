package com.green.onezo.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/about")
    public ResponseEntity<Menu> getAbout(@PathVariable String origin,@PathVariable String nutriernt) {
        Optional<Menu> menuOptional = menuService.menuAbout(origin, nutriernt);
        if(menuOptional.isPresent()){
            Menu menu = menuOptional.get();
            return ResponseEntity.ok(menu);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //아이디 값으로 영양정보
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long id) {
        Optional<Menu> menuOptional = menuService.findById(id);
        if (menuOptional.isEmpty()){
            Menu menu = menuOptional.get();
            return ResponseEntity.ok(menu);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
