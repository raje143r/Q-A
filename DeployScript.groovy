pipeline{
        agent any
        options { timestamps() }
        stages{
              stage("Confirmation"){
               steps{
                        echo "${env.testpwd}"
                echo "${testpwd}"
                echo "%%env.testpwd%%"
                script{
                       	def workspace = pwd()
                        	echo "test workspacfull path ${workspace}"
                    wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: "${env.testpwd}", var: 'PSWD']]]) {
                        bat "echo PSWD: '${env.testpwd}'"
                         bat '''echo PSWD: ${param.passwd}'''
                    
                        input message: " Are you confirmed with the below parameters to deploy \n PWD: ${testpwd} \n project: ${params.profilechangeonly} \n ArchiveName: ${params.Earpath} \n BuildNumber: ${params.buildnumber}"
                        submitter: 'test,admin' 
                        ok: 'Release!'
                    }
                }
                }
               }
               stage("pre deploymentstep"){
               steps {
                        script {
                                    if (params.profilechangeonly == 'true') {
                                        echo 'Hello from main branch'
                                    }  else {
                                         echo "Hello from ${params.profilechangeonly} branch!"
                                    }
                            }
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
