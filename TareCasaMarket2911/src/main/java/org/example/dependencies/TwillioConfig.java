package org.example.dependencies;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;
public class TwillioConfig {
        // Find your Account Sid and Token at twilio.com/console
        public static final String ACCOUNT_SID = "ACeae16e0f619bbd85b98cd2053a826357";
        public static final String AUTH_TOKEN = "56dd127b4b140c54abab77f9d7c987b8";

        public static void SendMessage(String messages) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new PhoneNumber("whatsapp:+51970604059"),
                            new PhoneNumber("whatsapp:+14155238886"),

                            messages)
                    .create();

            System.out.println(message.getSid());
        }
    }

