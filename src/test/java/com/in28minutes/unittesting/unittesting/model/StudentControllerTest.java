package com.in28minutes.unittesting.unittesting.model;

import com.alibaba.fastjson.JSON;
import com.in28minutes.unittesting.unittesting.business.StudentService;
import com.in28minutes.unittesting.unittesting.business.StudentServiceImpl;
import com.in28minutes.unittesting.unittesting.controller.StudentController;
import com.in28minutes.unittesting.unittesting.data.StudentDAO;
import org.h2.command.dml.MergeUsing;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest:进行测试：只扫描@Controller，@ControllerAdvice标注的Bean，还有一些其他和Web层相关的Bean
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;//注入一个MockMvc对象，它用来伪造HTTP请求

    @MockBean
    private StudentService studentService;//由于在做测试时可能service的方法还没写完，所以把Service标注了@MockBean注解，作为一个mock对象

    @Test
    public void oneStudent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/one-student")//表示构造一个get方法，url为“/one-student”的Http请求
                .accept(MediaType.APPLICATION_JSON)//设置http头部的accept属性，表示客户端能够接受的参数类型为Json
                .param("id", "89");//带参数
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void allStudent() throws Exception {
        when(studentService.getAllStudent()).
                thenReturn(Arrays.asList
                        (new Student(1, 18, "zhangsan", "male", new Address("shanghai", "road")),
                                new Student(2, 19, "lisi", "male", new Address("shanghai", "road"))));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/all-student")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void addStudent() throws Exception {
        Student student = new Student(1, 18, "zhangsan", "male", new Address("shanghai", "road"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/add-student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(student));
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json("\"id\":89,\"age\":18,\"name\":\"zhangsan\",\"gender\":\"male\",\"address\":{\"city\":\"shanghai\",\"street\":\"road\"}"))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

}
