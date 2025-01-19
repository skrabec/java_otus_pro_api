pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Validate Dockerfile') {
            steps {
                script {
                    // Check if Dockerfile exists
                    if (!fileExists('Dockerfile')) {
                        error "Dockerfile not found in the repository root"
                    }

                    // Read Dockerfile content
                    def dockerfile = readFile('Dockerfile')

                    // Check for FROM instruction
                    if (!dockerfile.toLowerCase().trim().contains('from ')) {
                        error "Dockerfile validation failed: No source image provided with FROM instruction"
                    }

                    echo "Dockerfile validation passed"
                }
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Verify Allure Results') {
            steps {
                sh 'ls -la target/allure-results || true'
            }
        }

        stage('Publish allure report') {
            steps {
                script {
                    allure([
                            includeProperties: true,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'target/allure-results']],
                            report: 'target/allure-report'
                    ])
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        failure {
            echo 'Pipeline failed during Dockerfile validation'
        }
    }
}