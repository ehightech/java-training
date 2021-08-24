package com.homework.rpcfx.core.client.netty;

import com.alibaba.fastjson.JSON;
import com.homework.rpcfx.core.api.RpcfxRequest;
import com.homework.rpcfx.core.api.RpcfxResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;
import java.net.URI;

/**
 * @author bob
 */
public class RpcFxClient extends ChannelInboundHandlerAdapter {

  private RpcfxResponse rpcfxResponse;
  private RpcfxRequest rpcfxRequest;
  private String url;
  private final Object obj = new Object();

  public RpcFxClient(RpcfxRequest rpcfxRequest, String url) {
    this.rpcfxRequest = rpcfxRequest;
    this.url = url;
  }

  public RpcfxResponse connect(String host, int port) throws Exception {
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
      Bootstrap b = new Bootstrap();
      b.group(workerGroup);
      b.channel(NioSocketChannel.class);
      b.option(ChannelOption.SO_KEEPALIVE, true);
      b.handler(new ChannelInitializer<SocketChannel>() {
        @Override
        public void initChannel(SocketChannel ch) throws Exception {
          // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
          ch.pipeline().addLast(new HttpResponseDecoder());
          // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
          ch.pipeline().addLast(new HttpRequestEncoder());
          ch.pipeline().addLast(RpcFxClient.this);
        }
      });

      // Start the client.
      ChannelFuture f = b.connect(host, port).sync();

      URI uri = new URI(url);
      String msg = JSON.toJSONString(rpcfxRequest);
      DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0,
          HttpMethod.POST,
          uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));

      // 构建http请求
      request.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=utf-8");
      request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
      request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
      // 发送http请求
      f.channel().write(request);
      f.channel().flush();
      synchronized (obj) {
        // 未收到响应，使线程等待
        obj.wait();
      }
      f.channel().closeFuture().sync();
      return rpcfxResponse;
    } finally {
      workerGroup.shutdownGracefully();
    }
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof HttpResponse) {
      HttpResponse response = (HttpResponse) msg;
      System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaderNames.CONTENT_TYPE));
    }
    if (msg instanceof HttpContent) {
      HttpContent content = (HttpContent) msg;
      ByteBuf buf = content.content();
      String result = buf.toString(io.netty.util.CharsetUtil.UTF_8);
      System.out.println(result);
      rpcfxResponse = JSON.parseObject(result, RpcfxResponse.class);
      buf.release();
      synchronized (obj) {
        // 收到响应，唤醒线程
        obj.notifyAll();
      }
    }
  }

}
