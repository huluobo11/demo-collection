package com.elasticsearch.demo.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Document(indexName = "school", type = "_doc") // indexName为ES索引名，type为文档名
public class Student implements Serializable {

/*     id标识

     type字段类型
     index="true"，代表是否开启索引，默认开启
     analyzer="ik_max_word"代表搜索的时候是如何分词匹配，为IK分词器最细颗粒度
     searchAnalyzer = "ik_max_word"搜索分词的类型
     */

    //
    @Id
    private String id;


    @Field(type = FieldType.Keyword)
    private String name;

    private Integer age;

    @Field(type = FieldType.Double)
    private Double score;

    @Field(type = FieldType.Text)
    private String info;
}