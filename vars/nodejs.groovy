def lintchecks() {
        sh "echo Installing JSlist"
        sh "npm i jslint"
        sh "echo starting lintchecks for ${COMPONENT}"
        sh "node_modules/jslint/bin/jslint.js server.js || true"
        sh "echo lintChecks completed"
         

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
            stage('Sonar Checks') {
                steps {
                    sh "Excuting sonarchecks"
                    sh "sonar-scanner -Dsonar.host.url=http://${SONAR_URL}:9000/ $ARGS -Dsonar.projectKey=${COMPONENT} -Dsonar.login=${SONAR_CRED_USR} -Dsonar.password=${SONAR_CRED_PSW}"
                    sh "curl https://gitlab.com/thecloudcareers/opensource/-/raw/master/lab-tools/sonar-scanner/quality-gate > quality-gate.sh"
                }
            }
            stage('Generating Artifacts') {
                steps {
                    sh "echo Generating Artifacts..."
                    sh "npm install" 
                }
            }
        }
    } 

}