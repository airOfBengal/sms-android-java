package com.example.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
//            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
//                String messageBody = smsMessage.getMessageBody();
//                String number = smsMessage.getOriginatingAddress();
//
//                Toast.makeText(context, "SMS Received: "+ messageBody + "from: " +
//                        number, Toast.LENGTH_LONG).show();
//
//                Intent in = new Intent(context, MainActivity.class);
//                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(in);
//            }
//        }

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle=intent.getExtras();
            SmsMessage[] chunks=null;
            String number="";
            String message="";
            if(bundle!=null)
            {
                Object[] pdus=(Object[])bundle.get("pdus");
                chunks=new SmsMessage[pdus.length];
                for(int i=0;i<pdus.length;i++)
                {
                    chunks[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                    number=chunks[i].getOriginatingAddress();
                    message+=chunks[i].getMessageBody();
                }
            }

            Toast.makeText(context,"SMS Received: "+ message+", From: "+number,
                    Toast.LENGTH_LONG).show();

        }
    }
}
