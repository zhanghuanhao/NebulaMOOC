#!/bin/bash
nohup java -jar sso-server-1.0.0.jar &
nohup java -jar live-server-1.0.0.jar &
nohup java -jar web-server-1.0.0.jar &
