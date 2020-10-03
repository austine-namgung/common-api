package com.example.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "code")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_seq")
    private int codeSeq;
    @Column(name = "code_id")
    private String codeId;
    @Column(name = "code_name")
    private String codeName;
    @Column(name = "code_type")
    private String codeType;
    @Column(name = "code_type_name")
    private String codeTypeName;

}
