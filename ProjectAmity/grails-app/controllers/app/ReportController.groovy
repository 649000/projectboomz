package app
import grails.converters.JSON

class ReportController {

    def index = {
        // redirect (action: "loadData")
    }

    def save ={

        //After the mobile app initiated the url connection
        //Validation will be done on the mobile app (??)
        def resident = Resident.findByUserid(params.userid)

        def report = new Report();
        Calendar c = Calendar.getInstance();
        int date = c.get(Calendar.DATE)
        int month = c.get(Calendar.MONTH)+1
        int year =  c.get(Calendar.YEAR)

        //report.datePosted = date + "-" + month + "-" + year
        Date d = new Date()

        report.datePosted = d
        report.image = params.image
        report.title = params.title
        report.description = params.description
        report.category = params.category
        report.latitude = params.latitude
        report.longitude = params.longitude
        report.altitude = params.altitude
        report.status = "Pending"
        report.moderationStatus = false

        resident.addToReport(report)
    }

    def loadData = {

        //Need to retrieve only reports that are posted 6 months ago till now.

        def report = Report.createCriteria()
        def now = new Date()
        println("Today's date: "+ now)
        def results = report.list {
            and {
                //183 days is about 6 months
                between('datePosted',now-183,now)
                eq("moderationStatus", "true")

            }
        }
//        println(results.size())
//        for(Report r: results){
//            println(r.title)
//        }
        render result as JSON
    }


}
