package kj.activemq.main.producer.ptp;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

/**
 * activemq在点对点或队列模型
 * 消息队列消息生产者
 * @author yangjing
 *
 */
public class QueueSender {

	public static void main(String[] args) {
		//创建一个连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.253.1:61616");
		try {
			//从工厂对象中获得连接
			Connection connection = connectionFactory.createConnection();
			//开启连接
			connection.start();
			/*
			connection.createSession(paramA, paramB)
			A）paramA设置为true时：
			paramB的值忽略， acknowledgment mode被jms服务器设置 SESSION_TRANSACTED 。
			当一个事务被提交的时候，消息确认就会自动发生。
			B） paramA设置为false时：
			Session.AUTO_ACKNOWLEDGE为自动确认，当客户成功的从receive方法返回的时候，或者从
			MessageListener.onMessage方法成功返回的时候，会话自动确认客户收到的消息。
			Session.CLIENT_ACKNOWLEDGE 为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的
			acknowledge方法。jms服务器才会删除消息。（默认是批量确认）
			*/
			//开启一个回话，第一个参数指定不使用事务，第二个参数指定客户端接收消息的确认方式
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//创建一目的地Queue或者是Topic
			Queue queue = session.createQueue("mytestqueue");
			//创建一个生产者
			MessageProducer producer = session.createProducer(queue);
			//创建message
			TextMessage message = new ActiveMQTextMessage();
			message.setText("杨晶66666666666666666666");
			//发送消息
			producer.send(message);
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
