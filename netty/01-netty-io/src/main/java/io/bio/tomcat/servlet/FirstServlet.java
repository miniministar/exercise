package io.bio.tomcat.servlet;

import io.bio.tomcat.http.MyRequest;
import io.bio.tomcat.http.MyResponse;
import io.bio.tomcat.http.MyServlet;

public class FirstServlet extends MyServlet {
    @Override
    protected void doPost(MyRequest request, MyResponse response) throws Exception {
        response.write("this is First servlet");
    }

    @Override
    protected void doGet(MyRequest request, MyResponse response) throws Exception {
        this.doPost(request, response);
    }
}
