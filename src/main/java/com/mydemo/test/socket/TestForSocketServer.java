package com.mydemo.test.socket;

import com.alibaba.fastjson2.JSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TestForSocketServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress().getHostAddress() + "已连接");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("收到消息:" + bufferedReader.readLine());
            socket.shutdownInput();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("Hello");
            bufferedWriter.flush();
            System.out.println("发送消息: Hello");
            socket.shutdownOutput();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
