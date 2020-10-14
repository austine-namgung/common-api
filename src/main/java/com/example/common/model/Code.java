package com.example.common.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Entity
@Table(name = "code")
public class Code  implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_seq")
    @Schema(description = "코드 순서")
    private int codeSeq;

    @Column(name = "code_id")
    @Schema(description = "코드 ID")
    private String codeId;

    @Column(name = "code_name")
    @Schema(description = "코드 명")
    private String codeName;

    @Column(name = "code_type")
    @Schema(description = "코드 타입")
    private String codeType;

    @Column(name = "code_type_name")
    @Schema(description = "코드 타입명")
    private String codeTypeName;
}
