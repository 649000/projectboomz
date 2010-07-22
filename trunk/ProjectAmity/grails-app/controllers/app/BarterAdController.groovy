package app
import grails.converters.JSON

class BarterAdController {

    

    def index = {
     def resident=Resident.findByNric("S9000001A")
        def list= [BarterAd.findAllByResident(resident),BarterAd.findAllByResidentNot(resident)]
        [barters: list]
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
        def resident=Resident.findByNric("S9000001A")
        params.resident=resident;
        barter=new BarterAd(params)
        barter.save();
        redirect(action:'index')
    }

    def startAssign = {
        //System.out.print(params.toAssign)
//
//        System.out.print("hi im here")
//        System.out.print(params.toAssign)
//        Date today=new Date()
//        params.requestDatePosted=today
//        params.requestMessage=null
//        params.requestState="0"
//
//        def barter=new BarterRequest(params)
//        System.out.print(params)
//        barter.save()
//
//        System.out.print(barter.id)
//
//        def tempList=params.toAssign.split(",")
//
//        for(String s : tempList)
//        {
//            BarterAd b = BarterAd.get(s)
//            b.barterRequest = barter
//        }

//=====================================================
//        println(params)
//        Date today=new Date()
//        def tempList=params.data.split("/")
//params.tradeItems=tempList[0]
//params.requestedItems=tempList[1]
//params.requestId=tempList[2]
//params.tradeId=tempList[3]
//params.requestDatePosted=today
//def req=new BarterRequest(params)
//req.save()
//println(req.errors)
//===================================================

        def tempList=params.data.split("/")
        def residentR=Resident.findById(tempList[3])
        params.receiverUserID=residentR.userid
        params.subject="[Notice] "+residentR.userid+ " has requested to trade for your items"
        
        def html="";
        html+="<table width=\"960\" border=\"1\">"
html+=" <tr>"
html+=" <td colspan=\"2\">&nbsp;<b>"+residentR.userid+"</b> wants to trade with you:"
html+=" </tr>"
html+=" <tr>"
html+=" <td colspan=\"2\">&nbsp;Your items to trade:</td>"
html+=" </tr>"

        def tempList2=tempList[0].split(",")
        for(def i=0; i<tempList2.size(); i++)
        {
def tempBarter=BarterAd.findById(tempList2[i])

html+=" <tr>"
html+=" <td width=\"100\" height=\"100\"><img src=\"../../images/database/"+tempBarter.itemPhoto+"\"/></td>"
html+=" <td><b><u>"+tempBarter.itemName+"</b></u><br/>"
html+="Request made on:"+new Date()+"<br/>"
html+=tempBarter.itemDescription+"<br/>"
html+="</td>"
html+=" </tr>"

        }

html+=" <tr>"
html+=" <td colspan=\"2\">&nbsp;</td>"
html+=" </tr>"


        def tempList3=tempList[1].split(",")
        for(def i=0; i<tempList3.size(); i++)
        {
def tempBarter=BarterAd.findById(tempList3[i])

html+=" <tr>"
html+=" <td width=\"100\" height=\"100\"><img src=\"../../images/database/"+tempBarter.itemPhoto+"\"/></td>"
html+=" <td><b><u>"+tempBarter.itemName+"</b></u><br/>"
html+="Request made on:"+new Date()+"<br/>"
html+=tempBarter.itemDescription+"<br/>"
html+="</td>"
html+=" </tr>"

        }
html+="</table>"

        params.message=html;
        redirect(controller:'message',action:'send', params:params)

   }

    def checkRequest = {
        /System.out.println(barter)/
        def resident=Resident.findByNric("S9000001A")
        def tempList=BarterRequest.findAllByRequestId(resident.id)
        def tempUserResident=[];
        def tempPeopleResident=[];
        def tempUserList=[];
        def tempPeopleList=[];
        for(def i=0; i<tempList.size(); i++)
        {
            tempUserResident[i]=Resident.findById(tempList[i].requestId+"")
            tempPeopleResident[i]=Resident.findById(tempList[i].tradeId+"")
            def tempStringList=tempList[i].requestedItems.split(",")
            def tempPeopleListItem=[];
            for(def j=0; j<tempStringList.size(); j++)
            {
                tempPeopleListItem[j]=BarterAd.findById(tempStringList[j])
            }
            tempPeopleList[i]=tempPeopleListItem

            def tempStringList2=tempList[i].tradeItems.split(",")
            def tempUserListItem=[];
            for(def j=0; j<tempStringList2.size(); j++)
            {
                tempUserListItem[j]=BarterAd.findById(tempStringList2[j])
            }
            tempUserList[i]=tempUserListItem
        }
        def list=[tempUserResident,tempPeopleResident, tempUserList, tempPeopleList]
        /println(params.barter)/

//        params.receiverUserID=
//        params.subject=
//        params.message=

        render list as JSON
    }


    def generateData=
    {
        Random random = new Random()

        Date today=new Date()


        //System.out.println(params.itemDatePosted)

        def adjective=['Green', 'Red', 'Blue', 'Yellow', 'Purple', 'Black', 'White']
        def items=['Notepad', 'Blanket', 'Wallet', 'Pen', 'Pencil', 'Marker', 'Book', 'Chair', 'Table']

        def padjective=['green', 'red', 'blue', 'yellow', 'purple', 'black', 'white']
        def pitems=['pencil', 'book', 'bottle']
        
        for(def i=0; i<20; i++)
        {
            def barter;
            Resident a=new Resident()
            a.id=random.nextInt(9)+1;
            params.resident=a;
            def itemName=padjective[random.nextInt(7)]+pitems[random.nextInt(3)]
            params.itemName=itemName
            params.itemDatePosted=today
            params.itemPhoto=itemName+".png"
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

    def viewItem = {
        /System.out.println(barter)/
        def barter=BarterAd.findById(params.barter)
        def list=[barter, barter.resident]
        /println(params.barter)/
        render list as JSON
    }
}
