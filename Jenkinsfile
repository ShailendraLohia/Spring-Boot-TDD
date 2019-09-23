pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                withGradle(gradle: 'gradle 5.4') {
                    sh 'gradle clean build'
                }
            }
        }
        stage('Deploy') {
            steps {
                withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                                   	credentialsId   : 'PCF_LOGIN',
                                    usernameVariable: 'USERNAME',
                                    passwordVariable: 'PASSWORD']]) {

                    sh '/usr/local/bin/cf login -a http://api.run.pivotal.io -u $USERNAME -p $PASSWORD'
                    sh '/usr/local/bin/cf create-service cleardb spark Dev-db'
                    sh '/usr/local/bin/cf push'
                    }
            }

        }
    }
}