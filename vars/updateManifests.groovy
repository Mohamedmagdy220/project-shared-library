def call() {
    echo "Updating Kubernetes manifests..."

    def deploymentFile = readFile('k8s/app-deployment.yml')

    def imageName = "mohamed2200/blog_web_app"
    def newImageTag = "${imageName}:${env.BUILD_NUMBER}"

    // Replace the line containing the image
    def updatedFile = deploymentFile.replaceAll(
        /image:\s*${imageName}:\d+/,
        "image: ${newImageTag}"
    )

    writeFile(file: 'k8s/app-deployment.yml', text: updatedFile)

    echo "✅ Kubernetes deployment updated with new image tag: ${newImageTag}"
}
