package com.cjlim2007.discordbot;
import me.itsghost.jdiscord.DiscordAPI;
import me.itsghost.jdiscord.DiscordBuilder;
import me.itsghost.jdiscord.event.EventListener;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.exception.BadUsernamePasswordException;
import me.itsghost.jdiscord.exception.DiscordFailedToConnectException;
import me.itsghost.jdiscord.message.Message;
import me.itsghost.jdiscord.message.MessageBuilder;
public class MyBot implements EventListener{
	
	private DiscordAPI api;
	
	public MyBot() 
	{
		api = new DiscordBuilder().build();
		api.getEventManager().registerListener(this);
	}

	public void userSaysSomething(UserChatEvent event)
	{
		String message = event.getMsg().getMessage();
		if(message.equalsIgnoreCase("lenny"))
		{
			MessageBuilder builder = new MessageBuilder();
			builder.addString("( ͡° ͜ʖ ͡°)");
			Message reply = builder.build(api);
			event.getGroup().sendMessage(reply);
		}
	}
	
	public void connect() 
	{
		try {
			api.login("cjlim2010@yahoo.com","onlyontuesdays");
		} catch (BadUsernamePasswordException | DiscordFailedToConnectException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		MyBot bot = new MyBot();
		bot.connect();
	}

}
