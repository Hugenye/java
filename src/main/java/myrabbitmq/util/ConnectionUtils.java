package myrabbitmq.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtils {

	public static Connection getConnection() throws IOException, TimeoutException {
		///定义一个连接工厂
		ConnectionFactory factory=new ConnectionFactory();
		
		//设置服务地址
		factory.setHost("127.0.0.1");
		
		//amqp 协议 5672
		factory.setPort(5672);
		
		//vhost
		factory.setVirtualHost("/vhost_hgy");
		
		//用户名
		factory.setUsername("user_hgy");
		
		//密码
		factory.setPassword("123");
		
		return factory.newConnection();
		
		
		
	}
}
