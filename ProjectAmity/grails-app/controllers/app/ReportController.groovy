package app
import grails.converters.JSON
import org.apache.commons.codec.*
import org.apache.commons.codec.binary.Base64

class ReportController {

    def messageCheckingService
    def TwitterService
    
    def index = {
        // redirect (action: "loadData")
        // TwitterService.updateStatus(params.text)
        if(session.user != null)
        {
            params.messageModuleUnreadMessages = messageCheckingService.getUnreadMessages(session.user)
            [params : params]
        }
    }

    def saveOutdoor = {

        //After the mobile app initiated the url connection
        //Validation will be done on the mobile app
        try {
            def resident = Resident.findByUserid(params.userid)
            def report = new Report()
            Date d = new Date()
            report.datePosted = d
            report.image = params.imagename
            report.title = params.title
            report.description = params.description
            // report.category = "Outdoor"
            report.latitude = Double.parseDouble(params.latitude)
            report.longitude = Double.parseDouble(params.longitude)
            report.altitude = Double.parseDouble(params.altitude)
            report.status = "Pending"
            report.moderationStatus = false

            InputStream input = request.getInputStream()
            BufferedReader r = new BufferedReader(new InputStreamReader(input))
            StringBuffer buf = new StringBuffer()
            String line

            //Read the BufferedReader out and receives String data
            while ((line = r.readLine())!=null) {
		buf.append(line)
            }
            String imageString = buf.toString()
            Base64 b = new Base64()
            byte[] imageByteArray = b.decodeBase64(imageString)

            // FileOutputStream f = new FileOutputStream("/Users/nAzri/NetBeansProjects/ProjectAmity/web-app/outdoorreportimages/"+params.imagename)
            FileOutputStream f = new FileOutputStream("C:\\Documents and Settings\\Administrator\\My Documents\\NetBeansProjects\\ProjectAmity\\web-app\\outdoorreportimages\\"+params.imagename)
            f.write(imageByteArray);
            f.close();

            resident.addToReport(report)
            render "T"

        }
        catch(Exception e)
        {
            println("Error in saving outdoor report")
            e.printStackTrace()
            render "F"
        }
    }

    def saveIndoor = {
        println("Indoor reporting starts here")
        try {
            println(params.postalCode)
            def resident = Resident.findByUserid(params.userid)
            def _building = Building.findAllByPostalCode(params.postalCode)
            def theCorrectBuilding
            
            for(Building b: _building)
            {
                if(b.level==params.level && b.stairwell ==params.stairwell)
                {
                    println(b.level)
                    theCorrectBuilding = b
                }
            }
            def indoorReport = new IndoorReport()
        
            Date d = new Date()
            indoorReport.datePosted = d
            indoorReport.image = params.imagename
            indoorReport.title = params.title
            indoorReport.description = params.description
            // report.category = "Indoor"
  
            indoorReport.status = "Pending"
            indoorReport.moderationStatus = false

            InputStream input = request.getInputStream()
            BufferedReader r = new BufferedReader(new InputStreamReader(input))
            StringBuffer buf = new StringBuffer()
            String line

            //Read the BufferedReader out and receives String data
            while ((line = r.readLine())!=null) {
                buf.append(line)
            }
            String imageString = buf.toString()
            Base64 b = new Base64()
            byte[] imageByteArray = b.decodeBase64(imageString)

        
            FileOutputStream f = new FileOutputStream("/Users/nAzri/NetBeansProjects/ProjectAmity/web-app/indoorreportimages/"+params.imagename)
         //   FileOutputStream f = new FileOutputStream("C:\\Documents and Settings\\Administrator\\My Documents\\NetBeansProjects\\ProjectAmity\\web-app\\indoorreportimages\\"+params.imagename)
            f.write(imageByteArray);
            f.close();
        
           theCorrectBuilding.addToIndoorReport(indoorReport)
            resident.addToIndoorReport(indoorReport)
            render "T"
        }
        catch(Exception e)
        {
            println("Error in saving indoor report")
            e.printStackTrace()
            render "F"
        }
    }

    def loadData = {

        //Need to retrieve only reports that are posted 6 months ago till now.
        def buildingList = new ArrayList()
        def outdoorReport = Report.createCriteria()
        def indoorReport = IndoorReport.createCriteria()
        def now = new Date()

        //Retrieve all outdoor reports
        def outdoorResults = outdoorReport.list {
            and {
                //183 days is about 6 months
                between('datePosted',now-183,now)
                eq("moderationStatus", "true")

            }
        }
        //        def indoorResults = indoorReport.list {
        //                count("id")
        //            building {
        //               // count("id")
        //                groupProperty("postalCode")
        //
        //            }
        //        }

        def confusingList =  IndoorReport.executeQuery( "select b.postalCode, count(i.id), b.latitude,b.longitude from IndoorReport i, Building b where i.building.id = b.id group by b.postalCode" )

        //       for(def i=0;i<confusingList.size();i++)
        //       {
        //           println("Postal Code : " + confusingList[i][0] + "Amount of Reports: " + confusingList[i][1])
        //       }

        def list =[outdoorResults, confusingList]
        render list as JSON
    }


}
