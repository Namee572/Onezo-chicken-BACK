package com.green.onezo.purchase;

import com.green.onezo.pay.Pay;
import com.green.onezo.pay.PayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PayRepository payRepository;

}