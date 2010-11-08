package com.practicaljava.lesson30.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

@Stateless
public class MessageService {

    @Resource(mappedName = "jms/StockPrices")
    private Topic topic;

    @Resource(mappedName = "TopicConnectionFactory")
    private TopicConnectionFactory topicConnectionFactory;

    public void sendMessage(String text) {
        try {
            Connection connection = topicConnectionFactory.createConnection();
            Session session = connection.createSession(true, 0);
            MessageProducer producer = session.createProducer(topic);

            TextMessage textMessage = session.createTextMessage(text);
            producer.send(textMessage);

            connection.close();
        }
        catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
