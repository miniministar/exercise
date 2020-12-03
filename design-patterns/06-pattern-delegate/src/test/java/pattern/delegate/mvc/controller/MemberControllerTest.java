package pattern.delegate.mvc.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pattern.delegate.mvc.DispatcherServlet;
import pattern.delegate.mvc.TestServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.easymock.EasyMock.*;

public class MemberControllerTest{
    private TestServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        servlet = new TestServlet();
        //加载  alter + enter
        //import static org.easymock.EasyMock.createMock;
        request = createMock(HttpServletRequest.class);
        response = createMock(HttpServletResponse.class);
    }

    @Test
    public void getMemberById() {
        request.getRequestURI();
        //传入参数
//        request.getParameter("mid");
//        expectLastCall().andReturn("1");

        //回放
        replay(request);
        replay(response);
        //调用
        try {
            servlet.service(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @After
    public void tearDown(){
        //验证
        //import static org.easymock.EasyMock.verify;
        verify(request);
        verify(response);
    }

}