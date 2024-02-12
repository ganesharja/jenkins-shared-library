def lintchecks() {
        sh "echo starting lintchecks for ${COMPONENT}"
        sh "mvn checkstyle:check || true"
        sh "echo lintChecks completed for ${COMPONENT}"
         

}

def call () {
    
    pipeline {
        agent any 
        stages {
            stage('Lint Checks') {
                steps {
                    script {
                        lintchecks()
                    }
                }
            }
            stage('Code Compile') {
                steps {
                    sh "echo Generating Artifacts for ${COMPONENT}"
                    sh "mvn clean complete:
                }
            }
        }
    } 

}