pipeline {
    agent any

    tools {
        gradle 'Gradle 8.9'   // Jenkins Global Tool Configuration name for Gradle
        jdk 'Java 17'         // Jenkins Global Tool Configuration name
    }

    stages {
        stage('Checkout Test Repo') {
            steps {
                git branch: 'main',
                    credentialsId: 'a69c19ca-d53c-4d7f-8ae5-358de7c377c8gt',
                    url: 'https://github.com/mookto/HRMS_Software_BDD-With-Gradle.git'
            }
        }

//         stage('Build Project') {
//             steps {
//                 sh './gradlew clean build -x test'
//                 // -x test means skip tests in this stage, since we run them separately
//             }
//         }

        stage('Execute Tests') {
            steps {
                sh '"mvn clean test"
            }
        }

        stage('Archive Test Results') {
            steps {
                junit '**/build/test-results/test/*.xml'
                archiveArtifacts artifacts: '**/build/reports/cucumber/**', allowEmptyArchive: true
                archiveArtifacts artifacts: '**/build/cucumber-html-report/**', allowEmptyArchive: true
            }
        }

        stage('Publish HTML Report') {
            steps {
                publishHTML(target: [
                    reportName: 'Cucumber Report',
                    reportDir: 'build/reports/cucumber',
                    reportFiles: 'index.html',
                    keepAll: true
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline Completed.'
        }
        failure {
            echo 'Build failed! Check logs!'
        }
        success {
            echo 'Build succeeded!'
        }
    }
}
