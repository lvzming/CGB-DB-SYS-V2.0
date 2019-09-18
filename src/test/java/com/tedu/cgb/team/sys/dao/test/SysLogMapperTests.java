package com.tedu.cgb.team.sys.dao.test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tedu.cgb.team.sys.dao.SysLogDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLogMapperTests {
	@Autowired
	private SysLogDAO mapper;
	
	@Test
	public void testGetRowCount() {
		mapper.getRowCountByUsername("username");
	}
	
	@Test
	public void testRandom() {
		
		Random ran = new Random();
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 20; i++) {
			list.add(ran.nextInt(100));
		}
		System.out.println(list);
		Set<Integer> set = list.stream()
				.filter(x -> (x > 50))
				.distinct()
				.sorted()
//				.peek(i -> System.out.print(i + ", "))
//				.findFirst()
//				.orElse(-1);
				.collect(Collectors.toSet());
		System.out.println(set);
	}
	
	
}
