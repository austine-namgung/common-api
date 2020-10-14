package com.example.common.controller;


import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.common.model.Code;
import com.example.common.model.ResultMessage;
import com.example.common.repository.CommonRepository;
import com.example.common.utils.RedisManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/common")
@RequiredArgsConstructor
@Slf4j
public class CommonApiController {
    private final CommonRepository repository;
    private final static String CATEGORY_TYPE = "T01";
    private final static String MODEL_TYPE = "T02";  
      
    private final RedisManager<Code> redisManager;

    @Operation(summary = "카테고리 전부를 가져온다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 전부를 가져옴",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) }) })
    @GetMapping("/categories")   
    public List<Code> searchCategoryAll(){
        log.info("============check1==searchCategoryAll====");
        
        List<Code> categoryList  = redisManager.getListValue("common::category-all");
        if(categoryList !=null){
            log.info("============check2==redis-hit====");
            return categoryList;
        }
        categoryList = repository.findByCodeType(CATEGORY_TYPE);

        log.info("============check3==db-search====");

        redisManager.putList("common::category-all", categoryList, 10, TimeUnit.MINUTES);
        return categoryList;

    }

    @Operation(summary = "카테고리 코드 ID 로 코드의 상세내용을 가져온다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "코드를 가져옴",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Code.class)) }) })
    @GetMapping("/categories/{codeId}")
    public Code  searchCategory(@PathVariable String codeId){

        Code category = repository.findByCodeIdAndCodeType(codeId, CATEGORY_TYPE);
        return category;
    }

    @Operation(summary = "모델 전부를 가져온다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "모델 전부를 가져옴",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) }) })
    @GetMapping("/models")
    public List<Code> searchModelAll(){
        log.info("============check1==searchModelAll====");
        
        List<Code> modelList  = redisManager.getListValue("common::model-all");
        if(modelList !=null){
            log.info("============check2==cache hitttttt====");
            return modelList;
        }
        log.info("============check3==search DB ====");
        modelList = repository.findByCodeType(MODEL_TYPE);
        redisManager.putList("common::model-all", modelList, 20, TimeUnit.MINUTES);
        return modelList;

    }

    @Operation(summary = "모델 코드 ID 로 코드의 상세내용을 가져온다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "코드를 가져옴",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Code.class)) }) })
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
