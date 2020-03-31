package com.exercise.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ComponentScan(basePackages = {"com.exercise.controller"})
@WebAppConfiguration(value = "src/main/webapp")
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        //加载web容器上下文
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test(timeout = 1000, expected = NullPointerException.class)
    public void selectUser() throws Exception {

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/select")   // 数据的格式请求的url,请求的方法是 get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id", "1") // 添加参数
        );

        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(" response : "+ result);
        assertEquals(result, "select");
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void deleteUser() {
    }
}