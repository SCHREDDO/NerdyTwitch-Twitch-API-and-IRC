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
// Created On: 21.05.2018
// Last Edited On: 10.06.2018
// Language: Java
//
package io.github.schreddo.nerdy.twitch;

import io.github.schreddo.nerdy.twitch.irc.TwitchIRCClient;
import io.github.schreddo.nerdy.twitch.irc.connection.TwitchIRCConnection;

public class NerdyTwitch {

	private TwitchIRCConnection connection;
	private boolean extendedInformation;
	
	public TwitchIRCConnection getConnection() {
		return connection;
	}
	public void setConnection(TwitchIRCConnection connection) {
		this.connection = connection;
	}
	public boolean isExtendedInformation() {
		return extendedInformation;
	}
	public void setExtendedInformation(boolean extendedInformation) {
		this.extendedInformation = extendedInformation;
	}
	
	public NerdyTwitch() {
		setConnection(new TwitchIRCConnection());
		setExtendedInformation(true);
	}
	
	public NerdyTwitch(String username, String userPassword) {
		setConnection(new TwitchIRCConnection(username, userPassword));
		setExtendedInformation(true);
	}
	
	public NerdyTwitch(String username, String userPassword, boolean useSSL) {
		setConnection(new TwitchIRCConnection(username, userPassword, useSSL));
		setExtendedInformation(true);
	}
	
	public NerdyTwitch(String username, String userPassword, String serverAddress, boolean useSSL) {
		this(username, userPassword, serverAddress, useSSL, true);
	}
	
	public NerdyTwitch(String username, String userPassword, String serverAddress, int serverPort) {
		this(username, userPassword, serverAddress, serverPort, true);
	}
	
	public NerdyTwitch(String username, String userPassword, String serverAddress, boolean useSSL, boolean extendedInformation) {
		setConnection(new TwitchIRCConnection(username, userPassword, serverAddress, useSSL));
		setExtendedInformation(extendedInformation);
	}
	
	public NerdyTwitch(String username, String userPassword, String serverAddress, int serverPort, boolean extendedInformation) {
		setConnection(new TwitchIRCConnection(username, userPassword, serverAddress, serverPort));
		setExtendedInformation(extendedInformation);
	}
	
	public void setConnectionInformation(String username, String userPassword, boolean useSSL, boolean extendedInformation) {
		TwitchIRCClient.disconnect(getConnection());
		
		setConnection(new TwitchIRCConnection(username, userPassword, useSSL));
		setExtendedInformation(true);
	}
	
	public void setConnectionInformation(String username, String userPassword, String serverAddress, boolean useSSL, boolean extendedInformation) {
		TwitchIRCClient.disconnect(getConnection());
		
		setConnection(new TwitchIRCConnection(username, userPassword, serverAddress, useSSL));
		setExtendedInformation(extendedInformation);
	}
	
	public void setConnectionInformation(String username, String userPassword, String serverAddress, int serverPort, boolean extendedInformation) {
		TwitchIRCClient.disconnect(getConnection());
		
		setConnection(new TwitchIRCConnection(username, userPassword, serverAddress, serverPort));
		setExtendedInformation(extendedInformation);
	}
	
	public int connect() {
		return TwitchIRCClient.connect(getConnection(), isExtendedInformation());
	}
	
	public int disconnect() {
		return TwitchIRCClient.disconnect(getConnection());
	}
	
	public int restart() {
		return TwitchIRCClient.restart(getConnection(), isExtendedInformation());
	}
	
	public int joinChannel(String channelName) {
		return TwitchIRCClient.joinChannel(channelName, getConnection());
	}
	
	public int leaveChannel(String channelName) {
		return TwitchIRCClient.departChannel(channelName, getConnection());
	}
	
	public String readMessage() {
		return TwitchIRCClient.readMessage(getConnection());
	}
	
	public int sendServerMessage(String message) {
		return TwitchIRCClient.sendServerMessage(message, getConnection());
	}

	public int sendChannelMessage(String channelName, String message) {
		return TwitchIRCClient.sendChannelMessage(channelName, message, getConnection());
	}
	
	public int sendWisperMessage(String username, String message) {
		return TwitchIRCClient.sendWisperMessage(username, message, getConnection());
	}
}
