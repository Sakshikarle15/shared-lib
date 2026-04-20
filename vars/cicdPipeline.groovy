def call() {
    pipeline {
        agent any
        
        stages {
            stage('Checkout') {
                steps {
                    checkout scm
                }
            }
            
            stage('Build') {
                steps {
                    script {
                        echo "Building application..."
                        // Auto-detect project type and build
                        if (fileExists('package.json')) {
                            sh 'npm install'
                        } else if (fileExists('requirements.txt')) {
                            sh 'pip install -r requirements.txt'
                        }
                    }
                }
            }
            
            stage('Test') {
                steps {
                    script {
                        echo "Running tests..."
                        if (fileExists('package.json')) {
                            sh 'npm test || true'
                        } else if (fileExists('requirements.txt')) {
                            sh 'pytest || true'
                        }
                    }
                }
            }
            
            stage('Security Scan') {
                steps {
                    echo "Running security scans..."
                    // Add security scanning tools
                }
            }
            
            stage('Deploy') {
                steps {
                    echo "Deploying application..."
                    // Add deployment logic
                }
            }
        }
        
        post {
            success {
                echo "Pipeline completed successfully!"
            }
            failure {
                echo "Pipeline failed!"
            }
        }
    }
}