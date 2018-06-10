# NerdyTwitch-Twitch-API-and-IRC
![build version](https://img.shields.io/badge/version-0.6.0-brightgreen.svg)
![release version](https://img.shields.io/badge/release-v0.0.0-blue.svg)
![framework or language](https://img.shields.io/badge/Java-1.5-blue.svg)
![license](https://img.shields.io/badge/license-AGPL--3.0-lightgrey.svg)

### Current Version 0.0.0
### Development Version 0.6.0

## About
NerdyTwitch is a library to interact with the platform Twitch. The library give you methodes to use the Twitch API and the Twitch IRC. The library is programed in Java.

##### Info: 
- English isn’t my first language, so please excuse any mistakes.
- Contact me for proposals or questions.

## Features
### Features Timeline
#### Version 0.6.0
- handling IRC connection
- join channel
- leave channel
- read message
- send message
- send whisper message

### Upcoming Features
#### Twitch API
#### Twitch IRC
- [x] handling IRC connection
- [x] join channel
- [x] leave channel
- [ ] join room
- [ ] leave room
- [x] read message
- [x] send message
- [x] send whisper message
- [ ] check if connected

## Getting Started

## Usage
### Function / Methode
#### Class: NerdyTwitch  

| Name | Return | Description | Throws |
|------|--------|-------------|--------|
|NerdyTwitch||||
|setConnectionInformation|void|||
|connect|int|||
|disconnect|int|||
|restart|int|||
|joinChannel|int|||
|leaveChannel|int|||
|readMessage|String|||
|sendServerMessage|int|||
|sendChannelMessage|int|||
|sendWisperMessage|int|||

### Examples

## List of Error/Warning Codes
| Name | Description |
|------|-------------|

## Dependencies
### Runtime Dependencies
- Java 1.5 or higher
### Development Dependencies
- Java 1.5

## Unit Tests
No Unit Tests defined.

## Changelog
### = 0.6.0 June 10th 2018 =
#### Added
- connect to Twitch IRC
- disconnect from Twitch IRC
- restart the Twitch IRC connection
- join Twitch IRC channel
- leave Twitch IRC channel
- received message from Twitch IRC
- send message to Twitch IRC
- send message to a Twitch IRC channel
- send a wisper message to an user in the Twitch IRC

## Support Possibilities
- give proposals
- report bugs

## License
NerdyTwitch is released under the [AGPL-3.0](https://www.gnu.org/licenses/agpl-3.0.de.html) License.
