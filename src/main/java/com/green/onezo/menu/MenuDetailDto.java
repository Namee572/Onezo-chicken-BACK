package com.green.onezo.menu;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class MenuDetailDto {

    private Long id;

    private final Menu menu;
    private final List<MenuInfo> menuInfos;
    private final List<Nutrient> nutrients;
}
