// vars/pushManifests.groovy
def call() {
    echo "Pushing updated Kubernetes manifests to GitHub repository..."

    sh 'git config --global user.name "Jenkins"'
    sh 'git config --global user.email "jenkins@example.com"'

    withCredentials([usernamePassword(credentialsId: DOCKERHUB_CREDENTIALS, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        echo "Logging into DockerHub..."
                        sh "echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin"
    sh '''
	git checkout main

        git add k8s/app-deployment.yml
        if git diff --cached --quiet; then
          echo "⚠️ No changes to commit. Skipping commit step."
        else
           git commit -m "📦 Update Kubernetes deployment manifest with new image tag"
           echo "✅ Commit created successfully."
	   git push origin main

        fi
'''
    echo "Manifests have been successfully pushed to GitHub."
}
