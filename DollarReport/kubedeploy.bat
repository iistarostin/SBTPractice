docker build -t dollar ./DollarReport
docker build -t weather ./WeatherReport
docker build -t analysis .

kubectl create -f kube/dollar.yaml
kubectl create -f kube/db.yaml
kubectl create -f kube/weather.yaml
kubectl create -f kube/analysis.yaml

kubectl expose deployment analysis --type=LoadBalancer --port=8080