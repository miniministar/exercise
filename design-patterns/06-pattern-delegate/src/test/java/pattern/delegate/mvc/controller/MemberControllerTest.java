package pattern.delegate.mvc.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.w3c.dom.DOMConfiguration;
import pattern.delegate.mvc.DispatcherServlet;
import pattern.delegate.mvc.TestServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.easymock.EasyMock.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
//@ContextConfiguration(locations = {"classpath: resources/applicationContext.xml"})
public class MemberControllerTest{
    private TestServlet servlet;
//    private HttpServletRequest request;
//    private HttpServletResponse response;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        servlet = new TestServlet();
//        //加载  alter + enter
//        //import static org.easymock.EasyMock.createMock;
//        request = createMock(HttpServletRequest.class);
//        response = createMock(HttpServletResponse.class);

        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void getMemberById() {
//        request.getRequestURI();
//        //传入参数
////        request.getParameter("mid");
////        expectLastCall().andReturn("1");
//
//        //回放
//        replay(request);
//        replay(response);
//        //调用
//        try {
//            servlet.service(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        request.setRequestURI("/web/getMemberById.json");
        request.addParameter("mid", "1"); //直接添加request参数，相当简单
        try {
            servlet.service(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    @After
//    public void tearDown(){
//        //验证
//        //import static org.easymock.EasyMock.verify;
//        verify(request);
//        verify(response);
//    }

}