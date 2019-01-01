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
// Created On: 30.12.2018
// Last Edited On: 01.01.2019
// Language: Java
//
package io.github.schreddo.nerdy.twitch.api;

import java.util.HashMap;
import java.util.Map.Entry;

import io.github.schreddo.nerdy.twitch.api.http.request.HTTPRequest;
import io.github.schreddo.nerdy.twitch.api.http.request.enums.RequestMethod;
import io.github.schreddo.nerdy.twitch.api.http.request.models.Response;

public class TwitchAPIClient {
	private static String clientID;
	
	public static String getClientID() {
		return clientID;
	}
	public static void setClientID(String clientID) {
		TwitchAPIClient.clientID = clientID;
	}
	
	public static Response getClips(String broadcasterID, String gameID, String id, String after, String before, String endedAt, Integer first, String startedAt) {
		HTTPRequest httpRequest = new HTTPRequest("https://api.twitch.tv/helix/clips", RequestMethod.GET);
		httpRequest.addHeaderProperty("Client-ID", getClientID());
		
		HashMap<String, String> paramenterList = new HashMap<String, String>();
		paramenterList.put("broadcaster_id", broadcasterID);
		paramenterList.put("game_id", gameID);
		paramenterList.put("id", id);
		paramenterList.put("after", after);
		paramenterList.put("before", before);
		paramenterList.put("ended_at", endedAt);
		paramenterList.put("first", first.toString());
		paramenterList.put("started_at", startedAt);
		
		httpRequest = TwitchAPIClient.addParamenter(httpRequest, paramenterList);
		
		return httpRequest.executeRequest();
	}
	
	public static Response getTopGames(String after, String before, Integer first) {
		HTTPRequest httpRequest = new HTTPRequest("https://api.twitch.tv/helix/games/top", RequestMethod.GET);
		httpRequest.addHeaderProperty("Client-ID", getClientID());
		
		HashMap<String, String> paramenterList = new HashMap<String, String>();
		paramenterList.put("after", after);
		paramenterList.put("before", before);
		paramenterList.put("first", first.toString());
		
		httpRequest = TwitchAPIClient.addParamenter(httpRequest, paramenterList);
		
		return httpRequest.executeRequest();
	}
	
	public static Response getGames(String id, String name) {
		HTTPRequest httpRequest = new HTTPRequest("https://api.twitch.tv/helix/games", RequestMethod.GET);
		httpRequest.addHeaderProperty("Client-ID", getClientID());
		
		HashMap<String, String> paramenterList = new HashMap<String, String>();
		paramenterList.put("id", id);
		paramenterList.put("name", name);
		
		httpRequest = TwitchAPIClient.addParamenter(httpRequest, paramenterList);
		
		return httpRequest.executeRequest();
	}
	
	public static Response getStreams(String after, String before, String communityID, Integer first, String gameID, String language, String userID, String userLogin) {
		HTTPRequest httpRequest = new HTTPRequest("https://api.twitch.tv/helix/streams", RequestMethod.GET);
		httpRequest.addHeaderProperty("Client-ID", getClientID());
		
		HashMap<String, String> paramenterList = new HashMap<String, String>();
		paramenterList.put("after", after);
		paramenterList.put("before", before);
		paramenterList.put("community_id", communityID);
		paramenterList.put("first", first.toString());
		paramenterList.put("game_id", gameID);
		paramenterList.put("language", language);
		paramenterList.put("user_id", userID);
		paramenterList.put("user_login", userLogin);
		
		httpRequest = TwitchAPIClient.addParamenter(httpRequest, paramenterList);
		
		return httpRequest.executeRequest();
	}
	
	public static Response getStreamsMetadata(String after, String before, String communityID, Integer first, String gameID, String language, String userID, String userLogin) {
		HTTPRequest httpRequest = new HTTPRequest("https://api.twitch.tv/helix/streams/metadata", RequestMethod.GET);
		httpRequest.addHeaderProperty("Client-ID", getClientID());
		
		HashMap<String, String> paramenterList = new HashMap<String, String>();
		paramenterList.put("after", after);
		paramenterList.put("before", before);
		paramenterList.put("community_id", communityID);
		paramenterList.put("first", first.toString());
		paramenterList.put("game_id", gameID);
		paramenterList.put("language", language);
		paramenterList.put("user_id", userID);
		paramenterList.put("user_login", userLogin);
		
		httpRequest = TwitchAPIClient.addParamenter(httpRequest, paramenterList);
		
		return httpRequest.executeRequest();
	}
	
	public static Response getUsersFollows(String after, Integer first, String fromID, String toID) {
		HTTPRequest httpRequest = new HTTPRequest("https://api.twitch.tv/helix/users/follows", RequestMethod.GET);
		httpRequest.addHeaderProperty("Client-ID", getClientID());
		
		HashMap<String, String> paramenterList = new HashMap<String, String>();
		paramenterList.put("after", after);
		paramenterList.put("first", first.toString());
		paramenterList.put("from_id", fromID);
		paramenterList.put("to_id", toID);
		
		httpRequest = TwitchAPIClient.addParamenter(httpRequest, paramenterList);
		
		return httpRequest.executeRequest();
	}
	
	public static Response getVideos(String id, String userID, String gameID, String after, String before, Integer first, String language, String period, String sort, String type) {
		HTTPRequest httpRequest = new HTTPRequest("https://api.twitch.tv/helix/videos", RequestMethod.GET);
		httpRequest.addHeaderProperty("Client-ID", getClientID());
		
		HashMap<String, String> paramenterList = new HashMap<String, String>();
		paramenterList.put("id", id);
		paramenterList.put("user_id", userID);
		paramenterList.put("game_id", gameID);
		paramenterList.put("after", after);
		paramenterList.put("before", before);
		paramenterList.put("first", first.toString());
		paramenterList.put("language", language);
		paramenterList.put("period", period);
		paramenterList.put("sort", sort);
		paramenterList.put("type", type);
		
		httpRequest = TwitchAPIClient.addParamenter(httpRequest, paramenterList);
		
		return httpRequest.executeRequest();
	}
	
	private static HTTPRequest addParamenter(HTTPRequest httpRequest, HashMap<String, String> urlParamenterList) {
		String key = "";
		String value = "";
		
		for(Entry<String, String> entry : urlParamenterList.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();
			if (value != null) {
				httpRequest.addURLParamenter(key, value);
			}
		}
		
		return httpRequest;
	}
}
