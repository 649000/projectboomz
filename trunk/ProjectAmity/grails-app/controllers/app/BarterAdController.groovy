package app
import grails.converters.JSON

class BarterAdController {

    def index = {
        redirect(action:"create")
    }

    def create = {
        
    }

    def assign = {
        def resident=Resident.findByNric("S9000001A")
        def list= [BarterAd.findAllByResident(resident),BarterAd.findAllByResidentNot(resident)]
        [barters: list]
    }

    def save = {
        def barter;
        Date today=new Date()
        params.itemDatePosted=today
        //System.out.println(params.itemDatePosted)
        Resident a=new Resident()
        a.id=1;
        params.resident=a;
        barter=new BarterAd(params)
        barter.save();
    }

    def startAssign = {
        //System.out.print(params.toAssign)

        System.out.print("hi im here")
        System.out.print(params.toAssign)
        Date today=new Date()
        params.requestDatePosted=today
        params.requestMessage=null
        params.requestState="0"
        
        def barter=new BarterRequest(params)
        System.out.print(params)
        barter.save()

        System.out.print(barter.id)

        def tempList=params.toAssign.split(",")

        for(String s : tempList)
        {
            BarterAd b = BarterAd.get(s)
            b.barterRequest = barter
        }

   }

    def generateData=
    {
        Random random = new Random()

        Date today=new Date()

        //System.out.println(params.itemDatePosted)

        def adjective=['Green', 'Red', 'Blue', 'Yellow', 'Purple', 'Black', 'White', 'Grey', 'Brown', 'Old', 'New', 'Thick', 'Thin']
        def items=['Notepad', 'Blanket', 'Wallet', 'Pen', 'Pencil', 'Marker', 'Book', 'Chair', 'Table']

        for(def i=0; i<50; i++)
        {
            def barter;
            Resident a=new Resident()
            a.id=random.nextInt(9)+1;
            params.resident=a;
            params.itemName=adjective[random.nextInt(12)]+" "+items[random.nextInt(9)]
            params.itemDatePosted=today
            barter=new BarterAd(params)
            barter.save();
        }
    }

    def search = {
        
    }

    def startSearch = {
        def list=BarterAd.findAllByItemNameLike("%"+params.search+"%")
        render list as JSON
    }

    def list = {

    }

    def update = {

    }

    def test= {

        
    }
}
