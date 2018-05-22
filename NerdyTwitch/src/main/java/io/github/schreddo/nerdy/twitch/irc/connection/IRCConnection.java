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
package io.github.schreddo.nerdy.twitch.irc.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class IRCConnection {
	private static BufferedReader connectionReceive;
	private static BufferedWriter connectionSend;
	private static Socket connectionSocket;
	private static Integer connectionStatus;
	
	private static BufferedReader getConnectionReceive() {
		return connectionReceive;
	}
	private static void setConnectionReceive(BufferedReader connectionReceive) {
		IRCConnection.connectionReceive = connectionReceive;
	}
	private static BufferedWriter getConnectionSend() {
		return connectionSend;
	}
	private static void setConnectionSend(BufferedWriter connectionSend) {
		IRCConnection.connectionSend = connectionSend;
	}
	private static Socket getConnectionSocket() {
		return connectionSocket;
	}
	private static void setConnectionSocket(Socket connectionSocket) {
		IRCConnection.connectionSocket = connectionSocket;
	}
	public static Integer getConnectionStatus() {
		return connectionStatus;
	}
	private static void setConnectionStatus(Integer connectionStatus) {
		IRCConnection.connectionStatus = connectionStatus;
	}

	public static int connect(String serverAddress, int serverPort) {
		try {
			IRCConnection.setConnectionStatus(3);
			
			IRCConnection.setConnectionSocket(new Socket(serverAddress, serverPort));
			IRCConnection.setConnectionSend(new BufferedWriter(new OutputStreamWriter(getConnectionSocket().getOutputStream())));
			IRCConnection.setConnectionReceive(new BufferedReader(new InputStreamReader(getConnectionSocket().getInputStream())));
			
			IRCConnection.setConnectionStatus(1);
		} catch (UnknownHostException e) {
			IRCConnection.setConnectionStatus(2);
			e.printStackTrace();
			
			return 1;
		} catch (IOException e) {
			IRCConnection.setConnectionStatus(2);
			e.printStackTrace();
			
			return 1;
		}
		
		return 0;
	}
	
	public static int disconnect() {
		try {
			IRCConnection.setConnectionStatus(4);
			
			IRCConnection.getConnectionReceive().close();
			IRCConnection.getConnectionSend().flush();
			IRCConnection.getConnectionSend().close();
			IRCConnection.getConnectionSocket().close();
			
			IRCConnection.setConnectionStatus(0);
		} catch (IOException e) {
			IRCConnection.setConnectionStatus(2);
			e.printStackTrace();
			
			return 1;
		}
		
		return 0;
	}
	
	public static int send(String message) {
		try {
			IRCConnection.getConnectionSend().write(message);
		} catch (IOException e) {
			IRCConnection.setConnectionStatus(2);
			e.printStackTrace();
			
			return 1;
		}
		
		return 0;
	}
	
	public static String receive() {
		String message = "";
		
		try {
			message = IRCConnection.getConnectionReceive().readLine();
		} catch (IOException e) {
			IRCConnection.setConnectionStatus(2);
			e.printStackTrace();
			
			return null;
		}
		
		return message;
	}
}
