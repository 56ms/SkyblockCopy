#!/bin/bash
pkill -f "Spigot.jar" && sleep 5
SERVER_DIR="/home/power16/Desktop/Spigot Test Server 1.21.10"
JAR="/home/power16/Desktop/SkyblockCopy/target/SkyblockCopy-1.0-SNAPSHOT.jar"

cp "$JAR" "$SERVER_DIR/plugins/" || exit 1
cd "$SERVER_DIR" || exit 1
exec ./start.sh    # replace with your server's actual .sh file name
