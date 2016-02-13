package com.cjlim2007.discordbot;

import org.apache.commons.codec.binary.Base64;
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
	/*String lastCommand = "nope";
	String tempCommand = "nah";
	String currentCommand;*/
	public void userSaysSomething(UserChatEvent event)
	{
		//defining stuff
		String command;
		String allArguments;
		String[] arguments;
		String message = event.getMsg().getMessage();
		String parts[] = message.split(" ", 2);
		command = parts[0];
		byte[] encodedBytes = null;
		byte[] encodedName = null;
		byte[] password = Base64.decodeBase64("dHN1bW8gcmluc2hhbiBrYWlob3U=");
		/*currentCommand = event.getUser().getUser().getId() + command;
		if (lastCommand.equals(currentCommand)) {
			spamblockplus ++;
			System.out.println(spamblockplus);
		} else {
			lastCommand = tempCommand;
			tempCommand = currentCommand;
			System.out.println(lastCommand);
			
		}
		if (spamblockplus >= 3) {
			tempDisconnect();
		}*/
		
		//setup for splitting commands so that the bot can read stuff easier
		if (parts.length==2) {
			allArguments = parts[1];
			//takes the second part of the message and splits it at - for aphorism
			arguments = allArguments.split("-", 2);
			//encodes the aphorism quote
			encodedBytes = Base64.encodeBase64(arguments[0].getBytes());
			//encodes the aphorism author
			encodedName = Base64.encodeBase64(arguments[arguments.length-1].getBytes());
		} else {
			//just in case something bad happens
			allArguments = "What?";
			arguments = null;
		}

		//array for the rng to print angelo's lines
		String[] angelo = {"What?", "Huh?", "What do you want from me?", "Oh gosh!","Who are you?!", "Isn't this just a shitposting topic?"};
		//deletes message and replaces it with a plain lenny
		if(command.equalsIgnoreCase("/lenny"))
		{
			event.getMsg().deleteMessage();
			MessageBuilder builder = new MessageBuilder();
			builder.addString("( ͡° ͜ʖ ͡°)");
			Message reply = builder.build(api);
			event.getGroup().sendMessage(reply);
		} //deletes message and replaces it with lenny final form
		else if (command.equalsIgnoreCase("/lennys")) {
			event.getMsg().deleteMessage();
			MessageBuilder builder = new MessageBuilder();
			builder.addString("           ( ͡° ͜ʖ ͡°)\n     ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)\n( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)");
			Message reply = builder.build(api);
			event.getGroup().sendMessage(reply);
		} //deletes message and shitposts for you
		else if (message.equalsIgnoreCase("angelo")) {
			event.getMsg().deleteMessage();
			MessageBuilder builder = new MessageBuilder();
			builder.addString(angelo[(int)(Math.random()*6)]);
			Message reply = builder.build(api);
			event.getGroup().sendMessage(reply);
		} //( ͡° ͜ʖ ͡°)
		else if (message.equalsIgnoreCase("delicious loli")) {
			MessageBuilder builder = new MessageBuilder();
			builder.addString("Did somebody say delicious loli? ( ͡° ͜ʖ ͡°) https://www.youtube.com/watch?v=d28V8wRdypI");
			Message reply = builder.build(api);
			event.getGroup().sendMessage(reply);
		} //exclusively for getting the confused responses
		else if (message.equalsIgnoreCase("arr yuu angero?")) {
			MessageBuilder builder = new MessageBuilder();
			builder.addString(angelo[(int)(Math.random()*2)]);
			Message reply = builder.build(api);
			event.getGroup().sendMessage(reply);
		} //deletes message then creates and aphorism link by encoding
		else if (command.equalsIgnoreCase("!aphorism")) {
			event.getMsg().deleteMessage();
			MessageBuilder builder = new MessageBuilder();
			builder.addString("https://aphorisms.kazamatsuri.org/?");
			builder.addString(new String(encodedBytes));
			builder.addString("?");
			builder.addString(new String(encodedName));
			Message reply = builder.build(api);
			event.getGroup().sendMessage(reply);
		} //deletes message then sends help to pm
		else if (command.equalsIgnoreCase("!help")) {
			event.getMsg().deleteMessage();
			MessageBuilder builder = new MessageBuilder();
			builder.addString("```!aphorism <quote> -<author>``` for aphorism\n```/lenny``` for a lenny\n```angelo``` for shitposting\n```delicious loli``` for a suprise ( ͡° ͜ʖ ͡°)\nyou may used this pm as a space to test out your shitposting needs");
			Message reply = builder.build(api);
			event.getUser().getUser().getGroup().sendMessage(reply);
		} //a function to stop the endless shitposting
		else if (command.equalsIgnoreCase("stoppu!")&&event.getUser().getUser().getId().equals("96439667074826240")) {
			event.getMsg().deleteMessage();
			MessageBuilder builder = new MessageBuilder();
			builder.addString(allArguments);
			Message reply = builder.build(api);
			event.getGroup().sendMessage(reply);
			tempDisconnect();
		} //a function to stop the shitposting until I manually run bot again
		else if (message.equalsIgnoreCase(new String (password))) {
			event.getMsg().deleteMessage();
			api.stop();
		}
	}
	public void tempDisconnect() {
		
		api.stop();
		try {
		    Thread.sleep(30000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		MyBot yumemi = new MyBot();
		yumemi.connect();
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
		MyBot yumemi = new MyBot();
		yumemi.connect();
	}

}
