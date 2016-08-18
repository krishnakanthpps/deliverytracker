package br.com.deliverytracker.receivingmanager.pushnotification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.deliverytracker.commom.JSonSerializer;
import br.com.deliverytracker.commom.ProtocolConsts;
import br.com.deliverytracker.commom.XMPPMessage;

/**
 * Created by tarcisio on 06/08/16.
 */
public class MessageSender {

    private static final String SENDER_ID = "497175095084@gcm.googleapis.com";

    private static final AtomicInteger msgId = new AtomicInteger();

    public static void SendMessage(XMPPMessage message) {
        RemoteMessage.Builder builder = new RemoteMessage.Builder(SENDER_ID) //
                .setMessageId(Integer.toString(msgId.incrementAndGet()));
        JSonSerializer.toJSON();
        for (Map.Entry<String, String> entry : msgData.entrySet()) {
            builder = builder.addData(entry.getKey(), entry.getValue());
        }
        FirebaseMessaging fm = FirebaseMessaging.getInstance();
        fm.send(builder.build());
    }
}
