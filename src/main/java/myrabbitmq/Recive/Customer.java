package myrabbitmq.Recive;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.client.AMQP.BasicProperties;

import myrabbitmq.util.ConnectionUtils;

public class Customer {

    private static final String QUEUE_NAME="test_simple_queue";

	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		// TODO Auto-generated method stub
      //获取链接
		Connection connection=ConnectionUtils.getConnection();
		
		//创建频道
		Channel channel=connection.createChannel();
		//队列声明
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//新的api 重新方法
		DefaultConsumer consumer=new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				// TODO Auto-generated method stub
//				super.handleDelivery(consumerTag, envelope, properties, body);
				String msgString=new String(body,"utf-8");
				System.out.println("new api=="+msgString);
			}
		};
		//监听 队列    
		channel.basicConsume(QUEUE_NAME, true,consumer);
		/*
		 * //定义队列的消费者 QueueingConsumer queueingConsumer =new QueueingConsumer(channel);
		 * //监听队列 channel.basicConsume(QUEUE_NAME,true,queueingConsumer); while (true) {
		 * Delivery delivery=queueingConsumer.nextDelivery(); String mString=new
		 * String(delivery.getBody()); System.out.println("[custumer] msg: "+mString); }
		 */
		
	}
}
