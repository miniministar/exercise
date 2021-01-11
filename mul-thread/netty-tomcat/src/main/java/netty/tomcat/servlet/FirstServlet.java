package netty.tomcat.servlet;

import netty.tomcat.http.MyRequest;
import netty.tomcat.http.MyResponse;
import netty.tomcat.http.MyServlet;

public class FirstServlet  extends MyServlet {
    @Override
    protected void doPost(MyRequest request, MyResponse response) throws Exception {
        response.write("This is First Serlvet");
    }

    @Override
    protected void doGet(MyRequest request, MyResponse response) throws Exception {
        doPost(request, response);
    }
}
