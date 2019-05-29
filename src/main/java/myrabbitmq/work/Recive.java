package myrabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import myrabbitmq.util.ConnectionUtils;

public class Recive {
	 private static final String QUEUE_NAME="work_queue";
	 public static void main(String[] args) throws IOException, TimeoutException {
		//获取连接
		 Connection connection=ConnectionUtils.getConnection();
		 //获取channel
		 Channel channel=connection.createChannel();
		 
		 //声明队列
		 channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		 
		 //定义一个消费者
		 Consumer consumer=new DefaultConsumer(channel) {
			 @Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				// TODO Auto-generated method stub
//				super.handleDelivery(consumerTag, envelope, properties, body);
				 String msg=new String(body);
				 System.out.println("{1} recive msg:"+msg);
				 try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally {
					System.out.println("{1} msg done");
				}
			}
		 };
		 //
		 boolean autoAck=true;
		 channel.basicConsume(QUEUE_NAME, autoAck,consumer);
	}
}
