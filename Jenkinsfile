node{
   stage('SCM Checkout'){
     git 'https://github.com/vasumathi-alexander/sonarproject.git'
   }
   stage('Compile-Package'){

      def mvnHome =  tool name: 'maven333', type: 'maven'   
      sh "${mvnHome}/bin/mvn clean package"
	  sh 'mv target/myweb*.war target/newapp.war'
   }
   stage('SonarQube Analysis') {
	        def mvnHome =  tool name: 'maven3', type: 'maven'
	        withSonarQubeEnv('sonar') { 
	          sh "${mvnHome}/bin/mvn sonar:sonar"
	        }
	    }
   stage('Build Docker Imager'){
   sh 'docker build -t vasumathi/myweb:0.0.2 .'
   }
   stage('Docker Image Push'){
   withCredentials([string(credentialsId: 'dockerPass', variable: 'dockerPassword')]) {
   sh "docker login -u vasumathi -p ${dockerPassword}"
    }
   sh 'docker push vasumathi/myweb:0.0.2'
   }
  stage('Nexus Image Push'){
   sh "docker login -u admin -p admin123 54.249.166.108:8083"
   sh "docker tag vasumathi/myweb:0.0.2 54.249.166.108:8083/damo:1.0.0"
   sh 'docker push 54.249.166.108:8083/damo:1.0.0'
   }

   stage('Remove Previous Container'){
	try{
		sh 'docker rm -f tomcattest'
	}catch(error){
		//  do nothing if there is an exception
	}
   stage('Docker deployment'){
   sh 'docker run -d -p 8090:8080 --name container2 vasumathi/myweb:0.0.2' 
   }
}
}
