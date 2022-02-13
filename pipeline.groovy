def mvn = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn"

pipeline {
    agent { label 'linux' }
    parameters {
        string(name: 'TAG', defaultValue: 'DNSTest', description: 'startup tag')
    }
    stages {
        stage('Build') {
            steps {
                sh "${mvn} compile"
            }
        }
        stage('Run Tests') {
            steps {
                sh "${mvn} test -Dtest=\"${params.TAG}\""
            }
        }
        stage('Allure Report Generation') {
            steps {
//                allure includeProperties: false,
//                        jdk: '',
//                        results: [[path: 'target/reports/allure-results']]
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/allure-results']]
            }
        }
    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}