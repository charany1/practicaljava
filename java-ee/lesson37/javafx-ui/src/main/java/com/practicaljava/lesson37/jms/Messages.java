package com.practicaljava.lesson37.jms;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class Messages {

    public static void startListening(MessageHandler messageHandler) throws Exception {
        System.out.println("Starting to listen");

        JMSListener jmsListener = new JMSListener(messageHandler);

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setProperty(ConnectionConfiguration.imqAddressList, "mq://localhost:7676");

        connection = connectionFactory.createQueueConnection("admin","admin");

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("Prices");
        MessageConsumer consumer = session.createConsumer(topic);

        consumer.setMessageListener(jmsListener);

        connection.start();

        System.out.println("Listening");
    }

    public static void stopListening() {
        try {
            connection.stop();
        }
        catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection connection;

    private static class JMSListener implements MessageListener {
        private MessageHandler messageHandler;

        private JMSListener(MessageHandler messageHandler) {
            this.messageHandler = messageHandler;
        }

        @Override
        public void onMessage(Message message) {
            if (! (message instanceof TextMessage)) {
                throw new IllegalArgumentException("Works only with text messages");
            }

            TextMessage textMessage = (TextMessage) message;

            try {
                System.out.println("Got message: " + textMessage.getText());

                messageHandler.handle(textMessage.getText());
            }
            catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
