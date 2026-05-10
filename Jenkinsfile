pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'taskmanager'
        TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                dir('app') {
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Unit Tests') {
            steps {
                dir('app') {
                    sh './mvnw test'
                }
            }
            post {
                success {
                    junit 'app/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE}:${TAG} -f app/Dockerfile app/"
                }
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker stop taskmanager-app || true
                    docker rm taskmanager-app || true
                    cd /var/jenkins_home/workspace/taskmanager-pipeline
                    docker-compose up -d --no-deps --build app
                '''
            }
        }
    }

    post {
        success {
            echo 'CI/CD Pipeline завершён успешно!'
        }
        failure {
            echo 'Ошибка в пайплайне. Проверьте логи!'
        }
    }
}
