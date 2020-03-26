package com.elasticsearch.demo;

import com.elasticsearch.demo.vo.Student;
import com.elasticsearch.demo.service.EsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class ElasticsearchDemoApplicationTests {

	@Autowired
	private EsService esService;

	@Test
	public void insert() {
		List<Student> students = new ArrayList<>();
		for (int i = 0; i <= 10; i++) {
			Student student = new Student();
			student.setId(i + "").setAge(10 + i).setName("王二小" + i).setScore(i + 72.5).setInfo("大王派我来巡山" + i);
			students.add(student);
		}
		esService.addAll(students);
		assert students.size() > 0;

		Assertions.assertDoesNotThrow(students::size);
	}

	@Test
	public void fuzzySearch() {
		List<Student> list = esService.findByName("二小");
		list.forEach(System.err::println);
		Assertions.assertDoesNotThrow(list::size);
	}

}
