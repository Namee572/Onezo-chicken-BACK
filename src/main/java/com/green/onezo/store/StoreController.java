package com.green.onezo.store;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
@Tag(name = "store-controller", description = "매장")
public class StoreController {

    private final StoreService storeService;
    private final StoreRepository storeRepository;

    @GetMapping("/store/{storeId}/detail")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id) {
        StoreDto storeDto = storeService.getStoreById(id);
        return new ResponseEntity<>(storeDto, HttpStatus.OK);
    }

    //매장 식사 + 포장 여부
    @Operation(summary = "매장 식사 여부")
    @PostMapping("/orderType")
    public ModelAndView orderType(@RequestParam("orderType") OrderType orderType){
        try{
            storeRepository.findByOrderType(orderType);
            ModelAndView modelAndView = new ModelAndView("주문 성공");
            modelAndView.addObject("Order",orderType);
            modelAndView.addObject("message","주문이 성공적으로 완료 되었습니다.");
            return modelAndView;
        }catch (Exception e){
            ModelAndView modelAndView = new ModelAndView("주문 실패");
            modelAndView.addObject("error","주문 처리 중 오류가 발생 했습니다.");
            return modelAndView;
        }
    }

    //매장주소 리스트
    @Operation(summary = "매장 주소 리스트")
    @GetMapping("/storeList")
    public ResponseEntity<List<StoreDto>>getStoreAddress(StoreDto storeDto){
        List<StoreDto> storeDtoList = storeService.storeDto(
                storeDto.getId(),
                storeDto.getStoreName(),
                storeDto.getAddress(),
                storeDto.getAddressOld());
        if (storeDtoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(storeDtoList);
    }

}