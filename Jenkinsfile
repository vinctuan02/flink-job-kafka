pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                // Xây dựng dự án với Maven (hoặc Gradle nếu bạn sử dụng)
                sh 'mvn clean package -DskipTests' // Sử dụng Maven để build, bỏ qua kiểm tra
            }
        }
        stage('Test') {
            steps {
                script {
                    echo 'Running the application...'
                    // Chạy file .jar với Java 11
                    sh 'java -jar target/flink1-1.0-SNAPSHOT.jar' // Thay thế 'your-app.jar' bằng tên file jar của bạn
                }
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
