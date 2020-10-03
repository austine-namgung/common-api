package com.example.common.controller;


import java.util.List;

import com.example.common.model.Code;
import com.example.common.model.ResultMessage;
import com.example.common.repository.CommonRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/common")
@RequiredArgsConstructor
public class CommonApiController {
    private final CommonRepository repository;
    private final static String CATEGORY_TYPE = "T01";
    private final static String MODEL_TYPE = "T02";

    @GetMapping("/categories")
    public List<Code> searchCategoryAll(){

        List<Code> categoryList = repository.findByCodeType(CATEGORY_TYPE);
        return categoryList;

    }

    @GetMapping("/categories/{codeId}")
    public Code  searchCategory(@PathVariable String codeId){

        Code category = repository.findByCodeIdAndCodeType(codeId, CATEGORY_TYPE);
        return category;
    }

    @GetMapping("/models")
    public List<Code> searchModelAll(){

        List<Code> modelList = repository.findByCodeType(MODEL_TYPE);
        return modelList;

    }

    @GetMapping("/models/{codeId}")
    public Code  searchModel(@PathVariable String codeId){

        Code model = repository.findByCodeIdAndCodeType(codeId, MODEL_TYPE);
        return model;
    }

    
    private ResponseEntity<ResultMessage> getResponseEntity(int result) {
		ResultMessage resultMessage;
		if (result > 0) {
			resultMessage = new ResultMessage("Y", "정상");
			return ResponseEntity.ok(resultMessage);
		}
		resultMessage = new ResultMessage("N", "오류");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMessage);

	}
    
}
