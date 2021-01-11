package io.bio.tomcat.http;

public abstract class MyServlet {
    public void service(MyRequest request, MyResponse response) throws Exception{
        if("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        }else {
            doPost(request, response);
        }
    }

    protected abstract void doPost(MyRequest request, MyResponse response) throws Exception;

    protected abstract void doGet(MyRequest request, MyResponse response) throws Exception;
}
