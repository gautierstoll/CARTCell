screen -dmS server1 taskset --cpu-list 1 ~/MaBoSS-env-2.0/engine/src/MaBoSS-server --port 20001 --host localhost --verbose
screen -dmS server2 taskset --cpu-list 2 ~/MaBoSS-env-2.0/engine/src/MaBoSS-server --port 20002 --host localhost --verbose
screen -dmS server3 taskset --cpu-list 3 ~/MaBoSS-env-2.0/engine/src/MaBoSS-server --port 20003 --host localhost --verbose
screen -dmS server4 taskset --cpu-list 4 ~/MaBoSS-env-2.0/engine/src/MaBoSS-server --port 20004 --host localhost --verbose

