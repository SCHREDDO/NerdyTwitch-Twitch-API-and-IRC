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
// Last Edited On: 21.05.2018
// Language: Java
//
package io.github.schreddo.nerdy.twitch.irc;

import io.github.schreddo.nerdy.twitch.irc.abstracts.IRCClient;
import io.github.schreddo.nerdy.twitch.irc.connection.IRCConnection;

public class TwitchIRCClient extends IRCClient{
	
	private boolean extendedInformation;
	
	public boolean getExtendedInformation() {
		return extendedInformation;
	}
	public void setExtendedInformation(boolean extendedInformation) {
		this.extendedInformation = extendedInformation;
	}

	public TwitchIRCClient() {
		this("", "", "irc.twitch.tv", 443, false);
	}
	
	public TwitchIRCClient(String botUsername, String botPassword) {
		this(botUsername, botPassword, "irc.twitch.tv", 443, false);
	}
	
	public TwitchIRCClient(String botUsername, String botPassword, boolean useSSL) {
		this(botUsername, botPassword, "irc.twitch.tv", 443, useSSL);
		
		if (!useSSL) {
			setServerPort(6667);
		}
	}
	
	public TwitchIRCClient(String botUsername, String botPassword, int serverPort) {
		this(botUsername, botPassword, "irc.twitch.tv", 443, false);
	}
	
	public TwitchIRCClient(String botUsername, String botPassword, boolean useSSL, boolean extendedInformation) {
		this(botUsername, botPassword, "irc.twitch.tv", useSSL, extendedInformation);
		
		if (!useSSL) {
			setServerPort(6667);
		}
	}
	
	public TwitchIRCClient(String botUsername, String botPassword, String serverAddress, boolean useSSL) {
		this(botUsername, botPassword, serverAddress, 443, useSSL);
		
		if (!useSSL) {
			setServerPort(6667);
		}
	}
	
	public TwitchIRCClient(String botUsername, String botPassword, String serverAddress, int serverPort) {
		this(botUsername, botPassword, serverAddress, serverPort, false);
	}
	
	public TwitchIRCClient(String botUsername, String botPassword, int serverPort, boolean extendedInformation) {
		this(botUsername, botPassword, "irc.twitch.tv", 443, extendedInformation);
	}
	
	public TwitchIRCClient(String botUsername, String botPassword, String serverAddress, boolean useSSL, boolean extendedInformation) {
		this(botUsername, botPassword, serverAddress, 443, extendedInformation);
		
		if (!useSSL) {
			setServerPort(6667);
		}
	}
	
	public TwitchIRCClient(String botUsername, String botPassword, String serverAddress, int serverPort, boolean extendedInformation) {
		super(serverAddress, serverPort, botUsername, botPassword);
		
		setExtendedInformation(extendedInformation);
	}
	
	@Override
	public int connect() {
		
		if (IRCConnection.getConnectionStatus() == 0) {
			int isConnected = IRCConnection.connect(getServerAddress(), getServerPort());
			
			if (isConnected == 0) {
				IRCConnection.send("PASS " + getBotPassword() + "\r\n");
				IRCConnection.send("NICK " + getBotUsername() + "\r\n");
				
				if (getExtendedInformation()) {
					IRCConnection.send("CAP REQ :twitch.tv/membership \r\n");
					IRCConnection.send("CAP REQ :twitch.tv/commands \r\n");
					IRCConnection.send("CAP REQ :twitch.tv/tags \r\n");
				}
			} else {
				return 1;
			}
		}
		
		return 0;
	}

	@Override
	public int disconnect() {
		
		if (IRCConnection.getConnectionStatus() == 1) {
			IRCConnection.disconnect();
		}
		
		return 0;
	}

	@Override
	public int restart() {
		
		if (IRCConnection.getConnectionStatus() == 0 || IRCConnection.getConnectionStatus() == 2) {
			connect();
		}
		
		if (IRCConnection.getConnectionStatus() == 1) {
			disconnect();
			connect();
		}
		
		return 0;
	}
}
