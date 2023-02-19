pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy in Docker') {
            steps {
                sh 'docker compose up'
            }
        }
    }
}



