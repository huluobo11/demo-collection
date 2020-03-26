package com.elasticsearch.demo.service.impl;

import com.elasticsearch.demo.vo.QueryPage;
import com.elasticsearch.demo.vo.Student;
import com.elasticsearch.demo.dao.EsRepository;
import com.elasticsearch.demo.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsServiceImpl implements EsService {

    @Autowired
    private EsRepository esRepository;

    @Override
    public void add(Student student) {
        esRepository.save(student);
    }

    @Override
    public void addAll(List<Student> student) {
        esRepository.saveAll(student);
    }

    @Override
    public Page<Student> search(String keyword, QueryPage queryPage) {
        // es默认索引从0开始,mp默认从1开始
        PageRequest pageRequest = PageRequest.of(queryPage.getCurrent() - 1, queryPage.getSize());
        return esRepository.findByName(keyword, pageRequest);
    }

    @Override
    public List<Student> findByName(String name) {
        return esRepository.findByName(name);
    }
}
