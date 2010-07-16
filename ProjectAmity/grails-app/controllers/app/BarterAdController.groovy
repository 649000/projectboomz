package app

class BarterAdController {

    def index = {
        redirect(action:"create")
    }

    def create = {
        render("test")
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

    def search = {
        
    }

    def startSearch = {
        def list=BarterAd.findAllByItemNameLike("%"+params.search+"%")

        def html="<table><tr><td>Item Date</td><td>Name</td><td>Photo</td><td>Descrition</td><td>Category</td></tr>"

        list.each()
        {
            html+="<tr><td>"+"${it.itemDatePosted}"+"</td><td>"+"${it.itemName}"+"</td><td>"+"${it.itemPhoto}"+"</td><td>"+"${it.itemDescription}"+"</td><td>"+"${it.itemCategory}"+"</td></tr>"
        }

        html+="</table>"

        render html
    }

    def list = {

    }

    def update = {

    }
}
