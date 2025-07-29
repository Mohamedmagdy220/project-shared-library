def call() {
    echo "🛡️ Starting Trivy scan for image: $IMAGE_NAME:${env.BUILD_NUMBER}"
    sh '''
        echo "🔍 Running Trivy Scan..."
        trivy image --exit-code 0 --no-progress $IMAGE_NAME:${BUILD_NUMBER} || true
        echo "✅ Trivy scan completed."
	echo "🔁 Continuing pipeline after scan"
    '''
}
