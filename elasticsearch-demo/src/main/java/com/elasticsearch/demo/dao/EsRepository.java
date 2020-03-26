package com.elasticsearch.demo.dao;

import com.elasticsearch.demo.vo.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsRepository extends ElasticsearchRepository<Student, String> {

    /**
     * 根据学生姓名或信息模糊查询
     */
    Page<Student> findByName(String name, Pageable pageable);

    List<Student> findByName(String name);
}