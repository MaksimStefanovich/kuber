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
minikube image load api-gateway:1.0.0
minikube ssh
docker images

//create pod
kubectl run spring-app --image=client:1.0.0 --port=8080 --image-pull-policy Never

//apply manifest to kuber
kubectl apply -f config-map.yml

//to recognise ip and port for service
minikube service otel-agent-collector-monitoring -n spring-app --url

//
minikube addons enable ingress
//enable minikube ingress on 127.0.0.1
minikube tunnel

//
minikube ip

//create secret.yml
kubectl create secret generic book-service-db-secret --from-literal=username=postgres --from-literal=password=example -n spring-app

//CRD for opentelemetry collector insallation
kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.15.2/cert-manager.yaml

//opentelemetry open-collector operator
kubectl apply -f https://github.com/open-telemetry/opentelemetry-operator/releases/latest/download/opentelemetry-operator.yaml
