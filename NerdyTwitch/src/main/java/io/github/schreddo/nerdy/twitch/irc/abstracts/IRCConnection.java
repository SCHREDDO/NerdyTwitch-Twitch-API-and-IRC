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
// Last Edited On: 31.05.2018
// Language: Java
//
package io.github.schreddo.nerdy.twitch.irc.abstracts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class IRCConnection {
	private String serverAddress;
	private int serverPort;
	private BufferedReader connectionReceive;
	private BufferedWriter connectionSend;
	private Socket connectionSocket;
	private Integer connectionStatus;
	
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
	private BufferedReader getConnectionReceive() {
		return connectionReceive;
	}
	private void setConnectionReceive(BufferedReader connectionReceive) {
		this.connectionReceive = connectionReceive;
	}
	private BufferedWriter getConnectionSend() {
		return connectionSend;
	}
	private void setConnectionSend(BufferedWriter connectionSend) {
		this.connectionSend = connectionSend;
	}
	private Socket getConnectionSocket() {
		return connectionSocket;
	}
	private void setConnectionSocket(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
	}
	public Integer getConnectionStatus() {
		return connectionStatus;
	}
	private void setConnectionStatus(Integer connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	
	public IRCConnection() {
		this("", 0);
	}

	public IRCConnection(String serverAddress, int serverPort) {
		setServerAddress(serverAddress);
		setServerPort(serverPort);
		setConnectionReceive(null);
		setConnectionSend(null);
		setConnectionSocket(null);
		setConnectionStatus(0);
	}
	
	public int connect() {
		try {
			setConnectionStatus(3);
			
			setConnectionSocket(new Socket(getServerAddress(), getServerPort()));
			setConnectionSend(new BufferedWriter(new OutputStreamWriter(getConnectionSocket().getOutputStream())));
			setConnectionReceive(new BufferedReader(new InputStreamReader(getConnectionSocket().getInputStream())));
			
			setConnectionStatus(1);
		} catch (UnknownHostException e) {
			setConnectionStatus(2);
			e.printStackTrace();
			
			return 1;
		} catch (IOException e) {
			setConnectionStatus(2);
			e.printStackTrace();
			
			return 1;
		}
		
		return 0;
	}
	
	public int disconnect() {
		try {
			setConnectionStatus(4);
			
			getConnectionReceive().close();
			getConnectionSend().flush();
			getConnectionSend().close();
			getConnectionSocket().close();
			
			setConnectionStatus(0);
		} catch (IOException e) {
			setConnectionStatus(2);
			e.printStackTrace();
			
			return 1;
		}
		
		return 0;
	}
	
	public int send(String message) {
		try {
			getConnectionSend().write(message);
		} catch (IOException e) {
			setConnectionStatus(2);
			e.printStackTrace();
			
			return 1;
		}
		
		return 0;
	}
	
	public String receive() {
		String message = "";
		
		try {
			message = getConnectionReceive().readLine();
		} catch (IOException e) {
			setConnectionStatus(2);
			e.printStackTrace();
			
			return null;
		}
		
		return message;
	}
}
