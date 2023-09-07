package com.example.sms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sms.R;
import com.example.sms.model.SmsMessage;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SmsAdapter extends ArrayAdapter<SmsMessage> {
    public SmsAdapter(Context context, int resource, List<SmsMessage> smsMessageList){
        super(context, resource, smsMessageList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_sms, parent, false);
        }

        SmsMessage sms = getItem(position);

        TextView senderTextView = view.findViewById(R.id.senderTextView);
        TextView messageTextView = view.findViewById(R.id.messageTextView);

        senderTextView.setText(sms.getSender());
        messageTextView.setText(sms.getMessage());

        return view;
    }
}
