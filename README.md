<h2>Useful commands for building images for spring app and mysql db:</h2>
<h3>1. Execute docker-compose.yaml</h3>
<p><code>docker-compose up</code></p>

<br/>
<h2>Useful commands when working on Docker:</h2>
<h3>1. Build image</h3>
<p><code>docker build -t {image_name} .</code></p>

<h3>2. Show all running containers</h3>
<p><code>docker ps</code></p>

<h3>3. Show all containers</h3>
<p><code>docker ps -a</code></p>

<h3>4. Stop a container</h3>
<p><code>docker stop {container}</code></p>

<h3>5. Remove a container</h3>
<p><code>docker rm {container}</code></p>

<h3>6. Show all docker images</h3>
<p><code>docker images</code></p>

<h3>7. Remove a docker image. It can be removed when no container is using it.</h3>
<p><code>docker rmi {image}</code></p>

<h3>8. Run a image with specified container name and port mapping</h3>
<p><code>docker run -d --name {name} -p {LocalPort:DockerPort} {image}</code></p>

<h3>9. Show all volumes</h3>
<p><code>docker volume ls</code></p>

<h3>10. Remove all unused volumes</h3>
<p><code>docker volume prune</code></p>

<br/>
<h2>Useful commands that execute in interactive container environment:</h2>
<h3> 1. Execute container in an interactive mode. Suppose the container is mysql container:</h3>
<p><code>docker exec -it {container} bash</code></p>
<h3>2. Access mysql database</h3>
<p><code>mysql -uroot -p</code></p>
<h3>3. Enter root password</h3>
<p><code>Enter root password</code></p>
<h3>4. Show all database</h3>
<p><code>show databases;</code></p>
<h3>5. Select a database</h3>
<p><code>use {database};</code></p>
<h3>6. Show all tables of the selected database</h3>
<p><code>show tables;</code></p>

<br/>
<h2>Workflow for deploying containerized spring app and mysql db in minikube (Minikube is local environemnt for testing. Should be using cloud platform for deployment in production, such as AWS EKS):</h3>
<h3>1. Create deployment files. Each container should have at least 1 deployment file. Create deployment file for database and spring app (e.g. mysql-deployment.yaml , app-deployment.yaml)</h3>
<h3>2. Start cluster</h3>
<p><code>minikube start</code></p>
<h3>3. Create secrets (mysql root password)</h3>
<p><code>kubectl create secret generic {secret} --from-literal={key}={value}</code></p>
<ul>
<li>secret=mysql-root-pass</li>
<li>key=password</li>
<li>value=Efsios#@469</li>
</ul>
<h3>4. Create secrets (mysql username and password)</h3>
<p><code>kubectl create secret generic {secret} --from-literal={key1}={value1} --from-literal={key2}={value2}</code></p>
<ul>
<li>secret=mysql-user-pass</li>
<li>key1=username</li>
<li>value1=johnnyho</li>
<li>key2=password</li>
<li>value2=Efsios#@469</li>
</ul>
<h3>5. Create secrets (mysql database and mysql database url)</h3>
<p><code>kubectl create secret generic {secret} --from-literal={key1}={value1} --from-literal={key2}='{value2}'</code></p>
<ul>
<li>secret=mysql-db-url</li>
<li>key1=database</li>
<li>value1=software</li>
<li>key2=url</li>
<li>value2=jdbc:mysql://docker-mysql:3306/software</li>
</ul>
<h3>6. Deploy the database container to the cluster</h3>
<p><code>kubectl apply -f {path_of_db_deployment_file}</code></p>
<ul>
<li>path_of_db_deployment_file=deployments/mysql-deployment.yaml</li>
</ul>
<h3>7. Deploy the application container to the cluster</h3>
<p><code>kubectl apply -f {path_of_sprig_app_deployment_file}</code></p>
<ul>
<li>path_of_sprig_app_deployment_file=deployments/app-deployment.yaml</li>
</ul>
<h3>8. Forward the port of the service to local</h3>
<p><code>kubectl port-forward service/{service_name} {LocalPort:ServicePort}</code></p>
<ul>
<li>service_name=spring-app-server</li>
<li>LocalPort=8888</li>
<li>ServicerPort=8888</li>
</ul>
<h3>9. Deploy success. Can access via localhost:8888.</h3>

<br/>
<h2>Useful commands when working in k8s:</h2>
<h3>1. Show all resources in the cluster</h3>
<p><code>kubectl get all</code></p>
<h3>2. Show all secrets in the cluster</h3>
<p><code>kubectl get secrets</code></p>
<h3>3. Show all pods in the cluster</h3>
<p><code>kubectl get pods</code></p>
<h3>4. Show all deployments in the cluster</h3>
<p><code>kubectl get deployments</code></p>
<h3>5. Show all services in the cluster</h3>
<p><code>kubectl get services</code></p>
<h3>6. Delete multiple resources in the cluster</h3>
<p><code>kubectl delete {resource_path_1} {resource_path_2} {resource_path_3} {resource_path_4} ... </code></p>
<ul>
<li>resource_path_1=deployment.apps/spring-app-server</li>
<li>resource_path_2=deployment.apps/docker-mysql</li>
<li>resource_path_3=service/spring-app-server</li>
<li>resource_path_4=service/docker-mysql</li>
</ul>
<p>Noted that it is the way to delete a pod. When deleting a deployment, needs to delete its service at the same time</p>
<h3>7. Show the resource information</h3>
<p><code>kubectl describe {resource_path}</code></p>
<ul>
<li>resource_path=deployment.apps/spring-app-server</li>
</ul>
<h3>8. Show the error log of the resource</h3>
<p><code>kubectl logs {resource_path}</code></p>
<ul><li>resource_path=deployment.apps/spring-app-server</li></ul>
<h3>9. Show the current cluster</h3>
<p><code>kubectl config get-contexts</code></p>
<h3>10. Show all clusters</h3>
<p><code>kubectl config current-context</code></p>
