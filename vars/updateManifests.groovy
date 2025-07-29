def call() {
    echo "Updating Kubernetes manifests..."
    checkout scm
    sh 'git checkout main'
   
    def deploymentFile = readFile('k8s/app-deployment.yml')

    def imageName = "${IMAGE_NAME}"
    def newImageTag = "${imageName}:${env.BUILD_NUMBER}"

    // Replace the line containing the image
    def updatedFile = deploymentFile.replaceAll(
        /image:\s*${imageName}:\d+/,
        "image: ${newImageTag}"
    )

    writeFile(file: 'k8s/app-deployment.yml', text: updatedFile)

    echo "✅ Kubernetes deployment updated with new image tag: ${newImageTag}"
}
