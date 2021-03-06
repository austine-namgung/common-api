package com.example.common.controller;

import java.util.List;

import com.example.common.model.Code;
import com.example.common.model.ResultMessage;
import com.example.common.repository.CommonRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
public class CommonDataController {
    private final CommonRepository repository;

	@Operation(summary = "데이터를 삽입한다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "데이터를 삽입한다.",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = List.class)) }) })
    @PostMapping("/data-insert")
	public ResponseEntity<ResultMessage> insert( @RequestBody List<Code> listCode) throws Exception {
        for(Code code : listCode){
            repository.save(code);
        }
        return getResponseEntity(1);
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
