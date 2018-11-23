#!/bin/bash -eu

nohup java -cp ./update-druid-1.0-SNAPSHOT.jar:lib/* io.sugo.access.Main &
nodeType_PID=$!
echo "Start druid-update ($nodeType_PID)"


