pipeline{
       agent any
	     options { 
		     timestamps() 
		     skipDefaultCheckout(true)
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
			   }
        
     
}
