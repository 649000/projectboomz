package app
import grails.converters.JSON

class ReportController {

    def TwitterService
    def index = {
        // redirect (action: "loadData")
        // TwitterService.updateStatus(params.text)
    }

    def saveOutdoor = {

        //After the mobile app initiated the url connection
        //Validation will be done on the mobile app
        try {
            def resident = Resident.findByUserid(params.userid)
            def report = new Report();
            Date d = new Date()
            report.datePosted = d
            report.image = params.image
            report.title = params.title
            report.description = params.description
            // report.category = "Outdoor"
            report.latitude = params.latitude
            report.longitude = params.longitude
            report.altitude = params.altitude
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

            byte[] imageByteArray = Base64.decode(imageString);

            FileOutputStream f = new FileOutputStream("C://image.png");
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

        //After the mobile app initiated the url connection
        //Validation will be done on the mobile app
        def resident = Resident.findByUserid(params.userid)
        def _building = Building.findAllByPostalCode(params.postalCode)
        def theCorrectBuilding
        for(Building b: _building)
        {
            if(b.level==params.level && b.stairwell ==params.stairwell)
            {
                theCorrectBuilding = b;
            }
        }


        def indoorReport = new IndoorReport();
        //        Calendar c = Calendar.getInstance();
        //        int date = c.get(Calendar.DATE)
        //        int month = c.get(Calendar.MONTH)+1
        //        int year =  c.get(Calendar.YEAR)

        //report.datePosted = date + "-" + month + "-" + year
        
        Date d = new Date()
        indoorReport.datePosted = d
        report.image = params.image
        report.title = params.title
        report.description = params.description
        // report.category = "Indoor"
  
        report.status = "Pending"
        report.moderationStatus = false
        
        b.addToIndoorReport(indoorReport)
        resident.addToIndoorReport(indoorReport)
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
