node {
  stage("Clone the project") {
    git branch: 'main', url: 'https://github.com/YounesElhakimi/microservice_chatGPT_API.git'
  }

  stage("Compilation") {
    bat "./mvnw clean install -DskipTests"
  }

    stage("Runing unit tests") {
      bat "./mvnw test -Punit"
    }

    stage("Build And Run Docker Image ") {
      bat 'docker compose up'
   }

}