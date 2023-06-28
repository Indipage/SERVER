#!/bin/bash
BUILD_PATH=$(ls /home/ubuntu/SERVER/build/libs/indipage-0.0.1-SNAPSHOT.jar)

JAR_NAME=$(basename $BUILD_PATH)
echo "> build 파일명: $JAR_NAME"

echo "> 8080 포트 확인"
PORT=8080

PID=$(lsof -t -i:$PORT)

if [ -z "$PID" ]; then
  echo "> 8080 포트는 현재 사용 중이지 않습니다."
else
  echo "> 8080 포트를 사용 중인 프로세스의 PID: $PID"
  echo "> PID $PID 종료"
  kill -15 $PID
  sleep 5
fi

echo "> build 파일 배포"
nohup java -jar ./build/libs/indipage-0.0.1-SNAPSHOT.jar