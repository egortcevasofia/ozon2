package com.example.ozon2.controller;

import com.example.ozon2.Service.GoodService;
import com.example.ozon2.dto.GoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodController {

    private GoodService goodService;

    @Autowired
    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping
    public List<GoodDto> getGoods() throws FileNotFoundException {
        return goodService.getAllGoods();
    }

}
