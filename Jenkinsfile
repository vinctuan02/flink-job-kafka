pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                // Thêm lệnh build nếu có (như Maven, Gradle)
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                // Thêm lệnh test nếu cần
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying Hello World!'
                // Lệnh deploy nếu cần
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
