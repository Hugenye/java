package myrabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import myrabbitmq.util.ConnectionUtils;

public class Send {
    private static final String QUEUE_NAME="test_simple_queue";
	public static void main(String[] args) throws IOException, TimeoutException {
		//获取一个连接
		Connection connection=ConnectionUtils.getConnection();
		
		//从链接中获取一个通道
		Channel channel=connection.createChannel();
//		声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		String msg="hello simple";
		
		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		System.out.println("555555=="+msg);
		channel.close();
		connection.close();
	}
}
