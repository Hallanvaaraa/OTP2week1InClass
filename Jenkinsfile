pipeline {
    agent any
    environment {
                // Define SonarQube server name
                SONARQUBE_SERVER = 'SonarQubeServer'
                // Define SonarQube token
                SONARQUBE_TOKEN = 'squ_17d808cada009d8d8ded7f20fa3608f94f587352'
                // Define Docker Hub credentials ID
                DOCKERHUB_CREDENTIALS_ID = '797c8c7b-dc68-40fd-9269-60fe100f457c'
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
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(SONARQUBE_SERVER) {
                bat """
                sonar-scanner ^
                    -Dsonar.projectKey=devops-demo ^
                    -Dsonar.sources=src ^
                    -Dsonar.projectName=OTP2InClass ^
                    -Dsonar.host.url=http://localhost:9000 ^
                    -Dsonar.login=${env.SONARQUBE_TOKEN} ^
                    -Dsonar.java.binaries=target/classes ^
                """
                }
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