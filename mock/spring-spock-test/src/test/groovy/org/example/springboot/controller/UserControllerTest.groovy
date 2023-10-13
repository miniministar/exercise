package org.example.springboot.controller


import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import groovy.util.logging.Slf4j
import org.example.springboot.entity.User
import org.example.springboot.result.PageQuery
import org.example.springboot.result.PageResult
import org.example.springboot.result.Result
import org.example.springboot.result.ResultCode
import org.example.springboot.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification
import spock.lang.Title
import spock.mock.DetachedMockFactory

import java.nio.charset.Charset

import static org.assertj.core.api.Assertions.assertThat

@Title("UserController单元测试")
@WebMvcTest(controllers = [UserController.class])
@Slf4j
class UserControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc
    @Autowired
    IUserService userService

    /**
     * 每一个测试执行前都会先执行它
     * @return
     */
    def setup() {
        def userA = new User(id: 1, username: "A", nickname: "A")
        def userB = new User(id: 1, username: "B", nickname: "B")
        userService.get(_) >> userA
        userService.add(_) >> userA
        userService.modifyById(_) >> userA
        userService.delete(_) >> true
        userService.listAll(_) >> [userA, userB]
    }

    def "GetById"() {
        given: "参数"
        int id = 1;
        expect: ""
        def response = mockMvc
                .perform(MockMvcRequestBuilders.get(String.format("/springboot/user/getById?id=%d", id))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        //防止中文乱码
        def str = response.response.getContentAsString(Charset.forName("utf-8"));
        Result<User> result = JSON.parseObject(str, new TypeReference<Result<User>>(){});
        assertThat(result).isNotNull().hasFieldOrPropertyWithValue("code", ResultCode.SUCCESS.code);
        User data = result.getData();
        assertThat(data).isNotNull().hasFieldOrPropertyWithValue("id", id);
    }

    def "Add"() {
        given: "参数"
        User params = new User(username: "A", nickname: "A");
        expect: ""
        def response = mockMvc
                .perform(MockMvcRequestBuilders.get(String.format("/springboot/user/add"))
                        .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(params)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        //防止中文乱码
        def str = response.response.getContentAsString(Charset.forName("utf-8"));
        Result<User> result = JSON.parseObject(str, new TypeReference<Result<User>>(){});
        assertThat(result).isNotNull().hasFieldOrPropertyWithValue("code", ResultCode.SUCCESS.code);
        User data = result.getData();
        assertThat(data).isNotNull().hasFieldOrPropertyWithValue("username", "A");
    }

    def "Add validate user info"() {
        when: "校验"
        def response = mockMvc
                .perform(MockMvcRequestBuilders.get(String.format("/springboot/user/add"))
                        .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(params)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
        //防止中文乱码
        def str = response.response.getContentAsString(Charset.forName("utf-8"));
        Result result = JSON.parseObject(str, new TypeReference<Result>(){});

        then: "验证"
        result.code == expectedCode
        assertThat(result.getData()).hasFieldOrPropertyWithValue(field, expectedMessage)

        where: "测试数据"
        params            ||  expectedCode    | field         | expectedMessage
        getUser(10001)    | "A0400"           | "username"    | "用户名不能为空"
        getUser(10002)    | "A0400"           | "nickname"    | "昵称不能为空"
        getUser(10003)    | "A0400"           | "gender"    | "性别不能为空"
        getUser(10004)    | "A0400"           | "mobile"    | "电话不能为空"
    }

    def getUser(code) {
        User user = new User()
        def condition0 = {
            user.id = 1
        }
        def condition1 = {
            user.username = "zhansan"
        }
        def condition2 = {
            user.nickname = "张三"
        }
        def condition3 = {
            user.gender = 1
        }
        def condition4 = {
            user.mobile= "12345678901"
        }

        switch (code) {
            case 10000:
                condition0()
                break
            case 10001:
                user = new User()
                break
            case 10002:
                condition1()
                break
            case 10003:
                condition1()
                condition2()
                break
            case 10004:
                condition1()
                condition2()
                condition3()
                break
            case 10005:
                condition0()
                condition1()
                condition2()
                condition3()
                condition4()
                break
        }
        return user
    }

    def "ModifyById"() {
        when: "校验"
        def response = mockMvc
                .perform(MockMvcRequestBuilders.get(String.format("/springboot/user/modifyById"))
                        .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(params)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //防止中文乱码
        def str = response.response.getContentAsString(Charset.forName("utf-8"));
        Result result = JSON.parseObject(str, new TypeReference<Result>(){});

        then: "验证"
        result.code == code
        assertThat(result.getData()).hasFieldOrPropertyWithValue(field, value)

        where: "测试数据"
        params            ||  code                                 | field         | value
        getUser(10001)    | ResultCode.PARAM_ERROR.getCode()       | "id"          | "ID不能为空"
        getUser(10000)    | ResultCode.PARAM_ERROR.getCode()       | "username"    | "用户名不能为空"
        getUser(10005)    | ResultCode.SUCCESS.getCode()           | "id"          | 1
    }

    def "Delete"() {
        given: "参数"
        int id = 1;
        expect: ""
        def response = mockMvc
                .perform(MockMvcRequestBuilders.get(String.format("/springboot/user/delete?id=%d", id))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        //防止中文乱码
        def str = response.response.getContentAsString(Charset.forName("utf-8"));
        Result<Boolean> result = JSON.parseObject(str, new TypeReference<Result<Boolean>>(){});
        assertThat(result).isNotNull().hasFieldOrPropertyWithValue("code", ResultCode.SUCCESS.code);
        boolean data = result.getData();
        assertThat(data).isTrue();
    }

    def "List"() {
        given: "参数"
        def user = new User(id: 1);
        def params = PageQuery.builder().params(user).build();
        expect: ""
        def response = mockMvc
                .perform(MockMvcRequestBuilders.post("/springboot/user/list")
                        .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(params)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        //防止中文乱码
        def str = response.response.getContentAsString(Charset.forName("utf-8"));
        PageResult<User> result = JSON.parseObject(str, new TypeReference<PageResult<User>>(){});
        assertThat(result).isNotNull().hasFieldOrPropertyWithValue("code", ResultCode.SUCCESS.code);
        PageResult.Data data1 = result.getData();
        assertThat(data1.getList()).isNotEmpty().hasSize(2);
    }

    /**
     * 关于这个@TestConfiguration注解，是Spock官方建议的，
     * 在WebMvcTest的情况下使用，解决在声明变量的时候使用 Mock() 无效的问题
     */
    @TestConfiguration
    static class MockConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        IUserService userService() {
            return detachedMockFactory.Mock(IUserService)
        }
    }

}
