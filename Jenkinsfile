pipeline {
    agent any

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
    }
}