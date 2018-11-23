# INSA TP Spark & MLlib

## Participants

- Xavier DUSSERT-VIDALET
- Marc-Antoine FERNANDES

## Dataset

The dataset is coming from https://adresse.data.gouv.fr/data/


## Run Spark

First you need to run the master node with 
```bash
# From spark directory
sbin/start-master.sh
```

Next you need to launch at least one worker.
You will need the master URL which can be found in 
the UI interface or from the logs.
```bash
# From spark directory
sbin/start-slave.sh <master-url>
```

Run a scala script with
```bash
spark-shell -i <scala-script> --master spark://IP:PORT
```

Then just run function from the spark-shell
```
App.main
```