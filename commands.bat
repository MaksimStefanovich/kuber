minikube start
minikube stop

//collect metrics for minikube
minikube addons enable metrics-server

//enable UI for kuber
minikube dashboard

//create namespace
kubectl create namespace spring-app
// delete namespace
kubectl delete namespace spring-app

//change default namespace for kubectl
kubectl config set-context --current --namespace=spring-app

//
minikube -p minikube docker-env --shell powershell | Invoke-Expression

//load image from local docker to minikube docker
minikube image load client:1.0.0
minikube ssh
docker images

//create pod
kubectl run spring-app --image=client:1.0.0 --port=8080 --image-pull-policy Never

//apply manifest to kuber
kubectl apply -f pod.yml

//to recognise ip and port for service
minikube service client-service -n spring-app --url

//
minikube addons enable ingress

//
minikube ip