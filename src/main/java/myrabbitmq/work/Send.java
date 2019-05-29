package myrabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import myrabbitmq.util.ConnectionUtils;

public class Send {
	private static final String QUEUE_NAME="work_queue";
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		//获取链接
		Connection connection=ConnectionUtils.getConnection();
		
		//获取通道
		Channel channel=connection.createChannel();
		//声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		for (int i = 0; i < 50; i++) {
			String msg="hello "+i;
			System.out.println("[Send Msg]");
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
			Thread.sleep(i+20);
		}
		channel.close();
		connection.close();
	}
}
