package kj.activemq.main.consumer.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * activemq订阅模式消息消费者
 * @author yangjing
 *
 */
public class TopicConsumer {

	public static void main(String[] args) {
		//创建连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.253.1:61616");
		try {
			//创建连接
			Connection connection = connectionFactory.createConnection();
			connection.start();
			//创建一个会话
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//创建一个目标
			Destination destination = session.createTopic("mytopic");
			//创建一个消费者
			MessageConsumer consumer = session.createConsumer(destination);
			//接收消息
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					
					System.out.println(message);
					
				}
			});
			//暂停
			System.in.read();
			//关闭
			consumer.close();
			session.close();
			connection.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
