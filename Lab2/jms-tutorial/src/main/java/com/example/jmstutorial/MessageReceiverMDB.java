package com.example.jmstutorial;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/TestQueue")
        }
)
public class MessageReceiverMDB implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage msg) {
                System.out.println("MDB received: " + msg.getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
