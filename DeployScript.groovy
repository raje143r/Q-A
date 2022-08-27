pipeline{
        agent any
       
        stages{
              stage("Confirmation"){
               steps{
                script{
                        def name = "${params.project}"
                    def gender = "${params.project}"
                        def chk = "${project}"
                        def chk1= "${project}"
                        input message: " Are you confirm with the below parameters to deploy \n ProjectName: ${params.project} \n ArchiveName: ${params.archive} \n BuildNumber: ${params.buildnumber}"
                        submitter: 'test,admin' 
                        ok: 'Release!'
                }
                }
               }
               stage("pre deploymentstep"){
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
