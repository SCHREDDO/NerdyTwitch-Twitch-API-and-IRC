// Copyright (C) 2018-2019 Sebastian Lühnen
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
// Last Edited On: 01.01.2019
// Language: Java
//
package io.github.schreddo.nerdy.twitch;

import io.github.schreddo.nerdy.twitch.api.TwitchAPIClient;
import io.github.schreddo.nerdy.twitch.irc.TwitchIRCClient;
import io.github.schreddo.nerdy.twitch.irc.connection.TwitchIRCConnection;

public class NerdyTwitch {

	private TwitchIRCConnection connection;
	private boolean extendedInformation;
	
	private boolean useAPIJson;
	
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
	
	public boolean getUseAPIJson() {
		return useAPIJson;
	}
	public void setUseAPIJson(boolean useAPIJson) {
		this.useAPIJson = useAPIJson;
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
	
	public void setClientID(String clientID) {
		TwitchAPIClient.setClientID(clientID);
	}
	
	public boolean getAuthorization() {
		return false;
	}
	
	public NerdyTwitch json() {
		setUseAPIJson(true);
		return this;
	}
	
	public Object getClips(String broadcasterID, String gameID, String id) {
		return getClips(broadcasterID, gameID, id, null, null, null, null, null);
	}
	
	public Object getClips(String broadcasterID, String gameID, String id, String after, String before, String endedAt, Integer first, String startedAt) {
		Object responseData = null;
		responseData = TwitchAPIClient.getClips(broadcasterID, gameID, id, after, before, endedAt, first, startedAt).getResponse();
		
		if (!getUseAPIJson()) {
			responseData = jsonToObject(responseData.toString());
			setUseAPIJson(false);
		}
		
		return responseData;
	}
	
	public Object getTopGames() {
		return getTopGames(null, null, null);
	}
	
	public Object getTopGames(String afte, String before, Integer first) {
		Object responseData = null;
		responseData = TwitchAPIClient.getTopGames(afte, before, first).getResponse();
		
		if (!getUseAPIJson()) {
			responseData = jsonToObject(responseData.toString());
			setUseAPIJson(false);
		}
		
		return responseData;
	}
		
	public Object getGames(String id, String name) {
		Object responseData = null;
		responseData = TwitchAPIClient.getGames(id, name).getResponse();
		
		if (!getUseAPIJson()) {
			responseData = jsonToObject(responseData.toString());
			setUseAPIJson(false);
		}
		
		return responseData;
	}
	
	public Object getStreams() {
		return getStreams(null, null, null, null, null, null, null, null);
	}
	
	public Object getStreams(String after, String before, String communityID, Integer first, String gameID, String language, String userID, String userLogin) {
		Object responseData = null;
		responseData = TwitchAPIClient.getStreams(after, before, communityID, first, gameID, language, userID, userLogin).getResponse();
		
		if (!getUseAPIJson()) {
			responseData = jsonToObject(responseData.toString());
			setUseAPIJson(false);
		}
		
		return responseData;
	}
	
	public Object getStreamsMetadata() {
		return getStreamsMetadata(null, null, null, null, null, null, null, null);
	}
	
	public Object getStreamsMetadata(String after, String before, String communityID, Integer first, String gameID, String language, String userID, String userLogin) {
		Object responseData = null;
		responseData = TwitchAPIClient.getStreamsMetadata(after, before, communityID, first, gameID, language, userID, userLogin).getResponse();
		
		if (!getUseAPIJson()) {
			responseData = jsonToObject(responseData.toString());
			setUseAPIJson(false);
		}
		
		return responseData;
	}
	
	public Object getUsersFollows(StringBuffer fromID) {
		return getUsersFollows(null, null, fromID.toString(), null);
	}
	
	public Object getUsersFollows(String toID) {
		return getUsersFollows(null, null, null, toID);
	}
	
	public Object getUsersFollows(String after, Integer first, String fromID, String toID) {
		Object responseData = null;
		responseData = TwitchAPIClient.getUsersFollows(after, first, fromID, toID).getResponse();
		
		if (!getUseAPIJson()) {
			responseData = jsonToObject(responseData.toString());
			setUseAPIJson(false);
		}
		
		return responseData;
	}
	
	public Object getVideos(String id, String userID, String gameID, String after, String before, Integer first, String language, String period, String sort, String type) {
		Object responseData = null;
		responseData = TwitchAPIClient.getVideos(id, userID, gameID, after, before, first, language, period, sort, type).getResponse();
		
		if (!getUseAPIJson()) {
			responseData = jsonToObject(responseData.toString());
			setUseAPIJson(false);
		}
		
		return responseData;
	}
	
	private Object jsonToObject(String json) {
		return null;
	}
}
