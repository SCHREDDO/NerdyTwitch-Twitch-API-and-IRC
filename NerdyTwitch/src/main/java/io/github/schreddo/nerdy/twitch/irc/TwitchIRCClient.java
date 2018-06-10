// Copyright (C) 2018 SCHREDDO[Sebastian]
//
//
// This file is part of NerdyTwitch.
// 
// NerdyTwitch is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// NerdyTwitch is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with NerdyTwitch. If not, see <http://www.gnu.org/licenses/>.
//
//
// Created By: SCHREDDO[Sebastian]
// Created On: 21.05.2018
// Last Edited On: 10.06.2018
// Language: Java
//
package io.github.schreddo.nerdy.twitch.irc;

import io.github.schreddo.nerdy.twitch.irc.connection.TwitchIRCConnection;;

public class TwitchIRCClient {
	
	public static int connect(TwitchIRCConnection connection) {
		return connect(connection, true);
	}
	
	public static int connect(TwitchIRCConnection connection, boolean extendedInformation) {
		
		if (connection.getConnectionStatus() == 0) {
			int isConnected = connection.connect();
			
			if (isConnected == 0) {
				connection.send("PASS oauth:" + connection.getUserPassword() + "\r\n");
				connection.send("NICK " + connection.getUsername() + "\r\n");
				
				if (extendedInformation) {
					connection.send("CAP REQ :twitch.tv/membership \r\n");
					connection.send("CAP REQ :twitch.tv/commands \r\n");
					connection.send("CAP REQ :twitch.tv/tags \r\n");
				}
			} else {
				return 1;
			}
		}
		
		return 0;
	}

	public static int disconnect(TwitchIRCConnection connection) {
		
		if (connection.getConnectionStatus() == 1) {
			connection.disconnect();
		}
		
		return 0;
	}

	public static int restart(TwitchIRCConnection connection, boolean extendedInformation) {
		
		if (connection.getConnectionStatus() == 0 || connection.getConnectionStatus() == 2) {
			TwitchIRCClient.connect(connection, extendedInformation);
		}
		
		if (connection.getConnectionStatus() == 1) {
			TwitchIRCClient.disconnect(connection);
			TwitchIRCClient.connect(connection, extendedInformation);
		}
		
		return 0;
	}
	
	public static int joinChannel(String channelName, TwitchIRCConnection connection) {
		return TwitchIRCClient.sendServerMessage("JOIN #" + channelName, connection);
	}
	
	public static int departChannel(String channelName, TwitchIRCConnection connection) {
		return TwitchIRCClient.sendServerMessage("PART #" +channelName, connection);
	}
	
	public static String readMessage(TwitchIRCConnection connection) {
		String message = "";
		
		message = connection.receive();
		
		if (message.toLowerCase().startsWith("ping")) {
			TwitchIRCClient.sendServerMessage("PONG :tmi.twitch.tv", connection);
		}
		
		return message;
	}
	
	public static int sendServerMessage(String message, TwitchIRCConnection connection) {
		return connection.send(message + "\r\n");
	}
	
	public static int sendChannelMessage(String channelName, String message, TwitchIRCConnection connection) {
		return TwitchIRCClient.sendServerMessage("PRIVMSG #" + channelName + " :" + message, connection);
	}
	
	public static int sendWisperMessage(String username, String message, TwitchIRCConnection connection) {
		return TwitchIRCClient.sendServerMessage(".w " + username + " " + message, connection);
	}
}
