package kj.activemq.main.consumer.ptp;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * activemq在点对点或队列模型
 * 异步消息队列消息消费者
 * @author yangjing
 * 异步消费。客户可以为消费者注册一个消息监听器，以定义在消息到达时所采取的动作。
 * 实现MessageListener接口，在MessageListener（）方法中实现消息的处理逻辑。
 */
public class AsynchronousQueueConsumer {

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
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					if (message instanceof TextMessage) {
						String text = "";
						try {
							text = ((TextMessage)message).getText();
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(text);
					}
					
				}
			});
			System.in.read();
			//关闭
			consumer.close();
			session.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
