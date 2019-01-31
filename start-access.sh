#!/bin/bash -eu

nohup java -cp ./fuli-data-access-1.0-SNAPSHOT.jar:lib/* io.sugo.access.Main &
nodeType_PID=$!
echo "Start fuli-data-access ($nodeType_PID)"


