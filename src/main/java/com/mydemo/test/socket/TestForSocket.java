package com.mydemo.test.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
public class TestForSocket {

    // 需要配合socketServer 使用
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();

            socket.connect(new InetSocketAddress(8081));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("Hi");
            bufferedWriter.flush();
            socket.shutdownOutput();

            System.out.println("发送消息: Hi");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("收到消息:" + bufferedReader.readLine());
            socket.shutdownInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
