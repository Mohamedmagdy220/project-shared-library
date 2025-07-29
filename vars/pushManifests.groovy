def call(){
	echo "pushing updated "

    sh '''
        git config --global user.name "Jenkins"
        git config --global user.email "jenkins@example.com"

        git checkout main
        git add k8s/app-deployment.yml

        if git diff --cached --quiet; then
          echo "🛑 No changes to commit. Skipping commit step."
        else
          git commit -m "🧱 Update Kubernetes deployment manifest with new image tag"
          echo "✅ Commit created successfully."

          git remote set-url origin https://$GIT_USERNAME:$GIT_PASSWORD@github.com/Mohamedmagdy220/-CloudDevOpsProject.git
          git push origin main
        fi
    '''

}
