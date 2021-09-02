package com.tim40.tim40.controller;

import com.tim40.tim40.dto.LoyaltyCategoryDTO;
import com.tim40.tim40.dto.LoyaltyProgramDTO;
import com.tim40.tim40.model.LoyaltyCategory;
import com.tim40.tim40.model.enums.LoyaltyType;
import com.tim40.tim40.service.LoyaltyCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/loyaltyCategory")
public class LoyaltyCategoryController {

    @Autowired
    private LoyaltyCategoryService loyaltyCategoryService;

    @GetMapping("/{type}")
    public int getLoyaltyCategoryMinPoints(@PathVariable LoyaltyType type){
        return loyaltyCategoryService.getMinLoyaltyPoints(type);
    }

    @GetMapping("/category")
    public String getLoyaltyCategoryByMinPoints(@RequestParam("points") int points){
        return loyaltyCategoryService.getLoyaltyCategoryByPoints(points);
    }

    @PutMapping
        public void updateLoyaltyCategoryMinPoints(@RequestBody LoyaltyCategoryDTO loyaltyGroupDto){
        LoyaltyCategory loyaltyCategory = new LoyaltyCategory(null, loyaltyGroupDto.getType(), loyaltyGroupDto.getPoints());
        loyaltyCategoryService.updateLoyaltyPoints(loyaltyCategory);
    }
    
    @PostMapping("/add")
    	public String addLoyalty(@RequestBody LoyaltyProgramDTO lpdto) {
    		String s = loyaltyCategoryService.addLoyalty(lpdto);
    		return s;
    }
}
