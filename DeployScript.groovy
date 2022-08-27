pipeline{
        agent any
        stages{
              stage("CheckInputParametersBeforeproceeding"){
               steps{
                script{
                        def name = "${params.project}"
                    def gender = "${params.project}"
                        def chk = "${project}"
                        def chk1= "${project}"
                 input message: 'projectname : ${params.project}  archivename : ${params.archive}'
                         ok: 'Release!'
                }
                }
               }
               stage("predeploymentstep"){
               steps{
                bat "ant -f C:\\raj\\automationpoc\\esb\\stlfLoadForecast\\build_integ.xml copyfile Clean1"
            
               }
               }
               stage("deploycheck for password"){
                      steps{
                         script
                                { 
                                 def password = input message: 'Please enter the password', parameters: [string(defaultValue: '', description: '', name: 'password')]
                                        withCredentials([string(credentialsId: '9655419f-01e2-442d-b0e9-5ead17375eac', variable: 'CREDPASS')]) {
                                            if (password == env.CREDPASS) {
                                                echo "passwords match"
                                            } else {
                                                echo "passwords do not match"
                                            }
                                        }
                                }
                       }
               }           
      }
}
