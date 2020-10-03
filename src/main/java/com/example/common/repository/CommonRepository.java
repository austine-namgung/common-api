package com.example.common.repository;

import java.util.List;

import com.example.common.model.Code;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonRepository extends JpaRepository<Code, Integer> {

    List<Code> findByCodeType(String typeCode);

	Code findByCodeIdAndCodeType(String codeId, String categoryType);
    
}
