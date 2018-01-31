package com.solr.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

/**
 * @date 2017年10月24日上午11:10:31
 * @Description: TODO
 * @authorAdministrator
 */
public class TestActiveMq {
	/**
	 * 
	* @Description: 生产者--消息发送者
	*  @throws Exception    
	*  void
	 */
	//@Test
	public void testQueueProducer() throws Exception {
		// 1.先建立一个连接
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://101.200.59.200:61616");
		Connection connection = factory.createConnection();
		// 2.开启连接
		connection.start();
		// 3.创建一个session对象(第一个参数是否开启事务，第二个是自动应答或者手动应答。)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 4.创建一个Destination（queue形式或者topic形式）对象
		Destination queue = session.createQueue("queue");
		// 5.创建一个Producer对象
		MessageProducer producer = session.createProducer(queue);
		// 6.创建一个Message对象、
		TextMessage message = new ActiveMQTextMessage();
		message.setText("Hello ActiveMQ");
		// TextMessage createTextMessage = session.createTextMessage();
		// 7.发送消息
		producer.send(message);
		// 8.关闭资源
		producer.close();
		session.close();
		connection.close();

	}
	/**
	 * 
	* @Description: 消费者--消息接收者
	*  @throws Exception    
	*  void
	 */
	//@Test
	public void testQueueConsumer() throws Exception {
		// 1.先建立一个连接
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://101.200.59.200:61616");
		Connection connection = factory.createConnection();
		// 2.开启连接
		connection.start();
		// 3.创建一个session对象(第一个参数是否开启事务，第二个是自动应答或者手动应答。)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 4.创建一个Destination（queue形式或者topic形式）对象
		Destination queue = session.createQueue("queue");
		// 5.创建一个Consumer对象
		
		MessageConsumer consumer = session.createConsumer(queue);
		// 6.接收消息、
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage=(TextMessage)message;
				String textString="";
				try {
					textString=textMessage.getText();
				} catch (JMSException e) {
					e.printStackTrace();
				}
				System.out.println("++"+textString);
			}
		});
		System.in.read();
		// 7.关闭资源
		consumer.close();
		session.close();
		connection.close();

	}
	
	//@Test
	public void testTopicProducer() throws Exception{
		ConnectionFactory factory=new ActiveMQConnectionFactory("tcp://101.200.59.200:61616");
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("testTopic");
		MessageProducer producer = session.createProducer(topic);
		TextMessage message=new ActiveMQTextMessage();
		message.setText("消息2");
		producer.send(message);
		producer.close();
		session.close();
		connection.close();
	}
	//@Test
	public void testTopicConsumer()throws Exception{
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://101.200.59.200:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("消息2");
		MessageConsumer consumer = session.createConsumer(topic);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				//消息接收到以后的操作。
				TextMessage textMessage=(TextMessage)message;
				String textString="";
				 try {
					textString = textMessage.getText();
				} catch (JMSException e) {
					e.printStackTrace();
				}
				System.out.println(textString);
			}
		});
		System.in.read();
	}
}
