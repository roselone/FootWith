package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import edu.thu.cslab.footwith.client.helper.Message;
import edu.thu.cslab.footwith.client.helper.MessageAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Messages extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
        
        ListView messageList=(ListView)findViewById(R.id.listView_Messages);
        
        ArrayList<HashMap<String, Object>> listItem=new ArrayList<HashMap<String,Object>>();
        
        final Message message=new Message();
        message.requestMessage();
        listItem=message.getMessages();
        
        final MessageAdapter messageAdapter=new MessageAdapter(Messages.this, listItem);
        
        messageList.setAdapter(messageAdapter);
        
        messageList.setOnItemClickListener(new OnItemClickListener() {

        	@Override
        	public void onItemClick(AdapterView<?> adapterView, View itemView, int position,
					long arg3) {
				// TODO Auto-generated method stub
        		
        		final HashMap<String, Object> map=(HashMap<String, Object>)adapterView.getAdapter().getItem(position);
        		String type=(String)map.get("itemType");
        		
        		if (type.equals("systemMessage")){
        			
        		}
        		if (type.equals("contactMessage")){
               		LayoutInflater inflater=(LayoutInflater)Messages.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            		final LinearLayout layout=(LinearLayout)inflater.inflate(R.layout.message_clickcontactitem, null);
            		final String content=(String)map.get("itemContent");
            		final int pos=position;
            		final String sex=(String)map.get("sex");
            		final String name=(String)map.get("itemName");
            		TextView conteTextView=(TextView)layout.findViewById(R.id.message_ClickContactText);
            		conteTextView.setText(content);
            		
            		Builder builder=new Builder(Messages.this);
            		builder.setTitle("对"+name+"说..").setView(layout);
            		builder.setPositiveButton("回复", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

							EditText inputFeedBackEditText=(EditText)layout.findViewById(R.id.message_ClickContactEdit);
							String newFeedBack=inputFeedBackEditText.getText().toString();
							String newContent=content+"\n"+Login.userID+':'+newFeedBack;
							map.put("itemContent", newContent);
							message.modifyMessage(pos, map);
							messageAdapter.notifyDataSetChanged();
							Toast.makeText(Messages.this,
									"回复成功", Toast.LENGTH_SHORT)
									.show();
						}
					});
            		builder.setNeutralButton("邀请", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(Messages.this,
									"邀请已发出", Toast.LENGTH_SHORT)
									.show();
						}
					});
            		builder.setNegativeButton("算了", null);
            		builder.show();
        		}
        		if (type.equals("replyMessage")){
               		LayoutInflater inflater=(LayoutInflater)Messages.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            		final LinearLayout layout=(LinearLayout)inflater.inflate(R.layout.message_clickcontactitem, null);
            		final String content=(String)map.get("itemContent");
            		final int pos=position;
            		final String sex=(String)map.get("sex");
            		final String name=(String)map.get("itemName");
            		TextView conteTextView=(TextView)layout.findViewById(R.id.message_ClickContactText);
            		conteTextView.setText(content);

            		Builder builder=new Builder(Messages.this);
            		builder.setTitle("对"+name+"说..").setView(layout);
            		builder.setPositiveButton("回复", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

							EditText inputFeedBackEditText=(EditText)layout.findViewById(R.id.message_ClickContactEdit);
							String newFeedBack=inputFeedBackEditText.getText().toString();
							String newContent=content+"\n"+Login.userID+':'+newFeedBack;
							map.put("itemContent", newContent);
							message.modifyMessage(pos, map);
							messageAdapter.notifyDataSetChanged();
							Toast.makeText(Messages.this,
									"回复成功", Toast.LENGTH_SHORT)
									.show();
						}
					});
            		builder.setNegativeButton("算了", null);
            		builder.show();
        		}
        		if (type.equals("invitationMessage")){
        			final String title=(String)map.get("itemName");
        			String state=(String)map.get("itemState");
        			final int pos=position;
        			if (state.equals("unconfirmed")){
        				new Builder(Messages.this)
        					.setTitle("确认").setMessage("您打算加入么？")
        					.setPositiveButton("加入", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									map.put("itemName", title+" 【已加入】");
									map.put("itemState", "confirmed");
									message.modifyMessage(pos, map);
									messageAdapter.notifyDataSetChanged();
									Toast.makeText(Messages.this, 
											"加入成功！", Toast.LENGTH_SHORT)
											.show();
								}
							})
							.setNegativeButton("算了", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									Toast.makeText(Messages.this, 
											"您再考虑下", Toast.LENGTH_SHORT)
											.show();
								}
							})
							.show();
        			}
        		}
        	}
        });
	}
}