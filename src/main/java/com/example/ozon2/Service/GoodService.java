package com.example.ozon2.Service;

import com.example.ozon2.dto.GoodDto;
import com.example.ozon2.dto.ListOfGoodsDto;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class GoodService {

    private String jsonFile = ("C:\\Users\\segortseva\\IdeaProjects\\ozon2\\src\\main\\resources\\json\\goods.json");

    public List<GoodDto> getAllGoods() throws FileNotFoundException {
        Gson gson = new Gson();
        ListOfGoodsDto listOfGoodsDto = gson.fromJson(new FileReader(jsonFile), ListOfGoodsDto.class);
        return listOfGoodsDto.getListOfGoods();
    }


}
