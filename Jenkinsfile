pipeline {
    agent any
    environment {
                // Define Docker Hub credentials ID
                DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
                // Define Docker Hub repository name
                DOCKERHUB_REPO = 'tommish/cart'
                // Define Docker image tag
                DOCKER_IMAGE_TAG = 'latest'
            }
    tools {
        maven 'Maven3'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch:'main', url: 'https://github.com/Hallanvaaraa/OTP2week1InClass.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
        stage('Build Docker Image') {
                    steps {
                        script {
                            docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                        }
                    }
                }
                stage('Push Docker Image to Docker Hub') {
                    steps {
                        script {
                            docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                                docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                            }
                        }
                    }
                }
    }
}