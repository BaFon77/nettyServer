package com.nettyServer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger log = Logger.getLogger(ServerHandler.class.getName());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            byte[] incoming = (byte[]) msg;
            String decodedMessage = new String(incoming, StandardCharsets.UTF_8);
            log.info("Отправленное сообщение " + decodedMessage);

            byte[] response = new byte[incoming.length + 1];
            System.arraycopy(incoming, 0, response, 0, incoming.length);
            response[incoming.length] = 10;

            ctx.writeAndFlush(response);
        } catch (Exception e) {
            log.severe("Ошибка при обработке сообщения: " + e.getMessage());
        }
    }
}