package com.o2oSSM;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/16
 * 17:37
 * #
 */
@RunWith(SpringJUnit4ClassRunner.class)
//spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BaseTest {
}
