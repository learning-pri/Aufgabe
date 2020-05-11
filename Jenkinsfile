pipeline {
    agent any

    stages {
     	stage ('Init') {
            steps {
                echo "Init result: ${currentBuild.result}"
                echo "Init currentResult: ${currentBuild.currentResult}"
            }
            post {
                always {
                    echo "Post-Init result: ${currentBuild.result}"
                    echo "Post-Init currentResult: ${currentBuild.currentResult}"
                	}
                }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
	        steps {
	 			sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
              //  if(${currentBuild.currentResult} == "SUCCESS" || ${currentBuild.currentResult} == "UNSTABLE"){
			  //    if (isUnix()) {
						sh 'echo "Build Succeded."'
				        sh 'mvn clean package'
				        sh 'cp /data/eko/aufgabe/aufgabe-0.0.1-SNAPSHOT.jar /data/eko/aufgabe/aufgabe-0.0.1-SNAPSHOT.jar_DD_MMYYYY'
			//	      }else{
			//	        bat 'echo "Build Succeded."'
			//	      }
		   //  	}
            }
        }
    }
}

