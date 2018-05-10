package kj.activemq.main.producer.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

/**
 * 
 * activemq订阅模式消息队列生产者
 * @author yangjing
 *
 */
public class TopicProducer {

	public static void main(String[] args) {
		//创建连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.253.1:61616");
		try {
			//创建连接
			Connection connection = connectionFactory.createConnection();
			//开启连接
			connection.start();
			//创建一个回话
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//创建一个Destination，queue或者Topic
			Topic topic = session.createTopic("mytopic");
			//创建一个生成者
			MessageProducer producer = session.createProducer(topic);
			//创建一个消息
			TextMessage textMessage = new ActiveMQTextMessage();
			textMessage.setText("花菇凉来了！！！！！！！！！！！");
			//发送消息
			producer.send(textMessage);
			//关闭
			producer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
