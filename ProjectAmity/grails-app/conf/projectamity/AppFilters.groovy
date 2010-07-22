package projectamity

class AppFilters {

    def filters = {
        all(controller:'', action:'') {
            
            before = {
//                if(!session.user)
//                {
//                    redirect(controller:'index2')
//                }
            }
            after = {
                
            }
            afterView = {
                
            }
        }
    }
    
}
