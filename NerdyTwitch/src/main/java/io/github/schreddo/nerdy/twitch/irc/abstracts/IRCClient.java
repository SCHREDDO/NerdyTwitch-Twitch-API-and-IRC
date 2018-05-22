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
package io.github.schreddo.nerdy.twitch.irc.abstracts;

public abstract class IRCClient {
	
	private String serverAddress;
	private int serverPort;
	private String botUsername;
	private String botPassword;
	
	public String getServerAddress() {
		return serverAddress;
	}
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	public int getServerPort() {
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public String getBotUsername() {
		return botUsername;
	}
	public void setBotUsername(String botUsername) {
		this.botUsername = botUsername;
	}
	public String getBotPassword() {
		return botPassword;
	}
	public void setBotPassword(String botPassword) {
		this.botPassword = botPassword;
	}
	
	public IRCClient(String serverAddress, int serverPort, String botUsername, String botPassword) {
		setServerAddress(serverAddress);
		setServerPort(serverPort);
		setBotUsername(botUsername);
		setBotPassword(botPassword);
	}
	
	public abstract int connect();
	
	public abstract int disconnect();
	
	public abstract int restart();
}
