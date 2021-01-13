package netty.chat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatClientHandler extends ChannelInboundHandlerAdapter {
	  
    private Object response;    
      
    public Object getResponse() {    
	    return response;    
	}    
  
    @Override    
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        response = msg;
    }    
        
    @Override    
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {    
        System.out.println("client exception is general");    
    }    
} 
