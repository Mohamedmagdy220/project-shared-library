def call() {
    echo "Updating Kubernetes manifests..."

    def deploymentFile = readFile('k8s/app-deployment.yml')

    def newImageTag = "$IMAGE_NAME:${env.BUILD_NUMBER}"
    deploymentFile = deploymentFile.replaceAll("$IMAGE_NAME:.*", "$IMAGE_NAME:${newImageTag}")

    writeFile(file: 'k8s/app-deployment.yml', text: deploymentFile)

    echo "Kubernetes deployment updated with new image tag: ${newImageTag}"

}

