#!/bin/bash -eu

## Initializtion script for druid nodes
## Runs druid nodes as a daemon and pipes logs to log/ directory


nohup java -cp ./druid-update-1.0-SNAPSHOT.jar:lib/* io.sugo.access.Main &
nodeType_PID=$!
echo "Start druid-update ($nodeType_PID)"


