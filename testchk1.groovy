pipeline{
       agent any
	     options { 
		     timestamps() 
		     
	     }
        stages{
              stage("build"){
					   steps{
					   script {
							def workspace = pwd()
					  }
						echo "${workspace}"
						}
					   }
		
		stage("build1"){
					   steps{
					   script {
							def workspace = pwd()
					  }
						echo "${workspace}"
						}
					   }
			   }
        
     
}
