package kj.activemq.main.consumer.ptp;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * activemq在点对点或队列模型
 * 同步消息队列消息消费者
 * @author yangjing
 * 同步消费。通过调用消费者的receive方法从目的地中显式提取消息。
 * receive方法可以一直阻塞到消息到达。
 */
public class SynchronousQueueConsumer {

	public static void main(String[] args) {
		//创建一连接工厂
		ConnectionFactory connectionFactory  = new ActiveMQConnectionFactory("tcp://192.168.253.1:61616");
		try {
			//创建一个连接
			Connection connection = connectionFactory.createConnection();
			//打开连接
			connection.start();
			//创建一个回话
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//创建一个目的地Destination 
			Queue queue = session.createQueue("mytestqueue");
			//创建一个消费者
			MessageConsumer consumer = session.createConsumer(queue);
			while(true) {
				//设置接收者接收消息的时间，为了便于测试，这里定为100s
				Message message = consumer.receive(100000);
				if (message != null) {
					System.out.println(message);
				} else {
					//超时结束
					break;
				}
				
			}
			consumer.close();
			session.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
