kubectl delete -f kube/dollar.yaml
kubectl delete -f kube/db.yaml
kubectl delete -f kube/weather.yaml
kubectl delete -f kube/analysis.yaml

kubectl delete -n default service analysis