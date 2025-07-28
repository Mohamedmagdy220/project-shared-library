def call() {
    echo "Building Docker image..."   
    sh "cd docker/blog/
	docker build -t $IMAGE_NAME:${env.BUILD_NUMBER} ."
    echo "Docker image built successfully!"
}
