pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('Deploy') {
            steps {
                withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                                   	credentialsId   : 'PCF_LOGIN',
                                    usernameVariable: 'USERNAME',
                                    passwordVariable: 'PASSWORD']]) {

                    sh '/usr/local/bin/cf login -a http://api.run.pivotal.io -u $USERNAME -p $PASSWORD'
                    sh '/usr/local/bin/cf target -o Digital_Org -s development'
                    sh '/usr/local/bin/cf create-service cleardb spark Dev-db'
                    sh '/usr/local/bin/cf push'
                    }
            }

        }
    }
}