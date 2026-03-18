pipeline {
    agent any

    tools {
        maven 'maven-3'
    }

    environment {
        PATH = "/usr/local/bin:${env.PATH}"
        DOCKERHUB_USERNAME = 'juyint'
        DOCKERHUB_REPO = 'juyint/shopping-cart-localized'
        DOCKER_IMAGE_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Juyin-Tang/shopping-cart-localized.git'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    echo "=== Building JAR file ==="
                    mvn package

                    echo "=== JAR files ==="
                    ls -la target/*.jar

                    echo "=== Building Docker image ==="
                    docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} .
                '''
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([string(credentialsId: 'Docker_Hub', variable: 'DOCKER_PASS')]) {
                    sh '''
                        echo $DOCKER_PASS | docker login -u ${DOCKERHUB_USERNAME} --password-stdin
                        docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}
                    '''
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}