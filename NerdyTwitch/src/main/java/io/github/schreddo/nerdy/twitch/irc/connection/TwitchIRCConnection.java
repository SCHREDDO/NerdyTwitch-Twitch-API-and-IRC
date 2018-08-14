// Copyright (C) 2018 Sebastian Lühnen
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
// Created By: Sebastian Lühnen
// Created On: 31.05.2018
// Last Edited On: 31.05.2018
// Language: Java
//
package io.github.schreddo.nerdy.twitch.irc.connection;

import io.github.schreddo.nerdy.twitch.irc.abstracts.IRCConnection;

public class TwitchIRCConnection extends IRCConnection {
	
	private String username;
	private String userPassword;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public TwitchIRCConnection() {
		this("", "", "irc.twitch.tv", 443);
	}
	
	public TwitchIRCConnection(String username, String userPassword) {
		this(username, userPassword, "irc.twitch.tv", 443);
	}
	
	public TwitchIRCConnection(String username, String userPassword, boolean useSSL) {
		this(username, userPassword, "irc.twitch.tv", 443);
		
		if (!useSSL) {
			setServerPort(6667);
		}
	}
	
	public TwitchIRCConnection(String username, String userPassword, String serverAddress, boolean useSSL) {
		this(username, userPassword, serverAddress, 443);
		
		if (!useSSL) {
			setServerPort(6667);
		}
	}
	
	public TwitchIRCConnection(String username, String password, String serverAddress, int serverPort) {
		super(serverAddress, serverPort);
		
		setUsername(username);
		setUserPassword(password);
	}
}
