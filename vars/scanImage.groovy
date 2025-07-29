def call(){
	echo "scanning docker image..."
	sh "trivy image --exit-code 0 $IMAGE_NAME:${env.BUILD_NUMBER} "
}
