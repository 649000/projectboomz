var germ=null
var reportObj = null
var grabbedPlacemark = null
//var myPlacemark = null
function Init(response)
{
    reportObj = eval( '(' + response.responseText + ')')
    Germanium.CreateInstance("myGerm", InitSuccessCallback, InitFailureCallback);
}

function InitSuccessCallback(obj)
{
    germ = obj
    //germ.Load("http://www.germanium3d.com/static/sample/" + buildingObj.buildingName + ".xlcl", null, LoadOk, LoadFail);
    germ.Load("http://www.germanium3d.com/static/sample/generic_building/generic_building.xlcl", null, LoadOk, LoadFail);

         for(var i=0;i<roomObj.size();i++){
        // Create a placemark
        var myPlacemark = germ.CreatePlacemark()
        // Set placemark position
        var point = germ.CreatePointGeometry()

       // alert(roomObj[0].xcoordinate)
       alert(roomObj[0].xcoordinate)
       alert(roomObj[0].ycoordinate)
       alert(roomObj[0].zcoordinate)
       //convert to double.
      // point.SetPosition(roomObj[i].xcoordinate, roomObj[i].ycoordinate, roomObj[i].zcoordinate);

       

        myPlacemark.SetGeometry(point)
        // Add placemark to the scene
        germ.AddPlacemark(myPlacemark)
  }

// create a placemark for drag & drop to get coords
//var pmark = germ.CreatePlacemark("Item to drag and drop");
//germ.AddPlacemark(pmark);
//germ.AddEventHandler(Germanium.Event.OnPlacemarkActivated, GetPlacemarkForDragAndDrop);
//germ.AddEventHandler(Germanium.Event.OnMouseUp, DropPlacemark);
}

function InitFailureCallback(message, code)
{
    alert("Germanium initialization failed: " + message + "; error code: " + code)
}

// Will be called if loading finishes successfully
function LoadOk()
{
//alert(a.type)
//alert('Loading successful!');

}

// Will be called if loading fails
function LoadFail(errMsg, errCode)
{
    alert('Loading failed. Error code: ' + errCode + '; message: ' + errMsg)
}

//function GetPlacemarkForDragAndDrop(event)
//{
//   grabbedPlacemark = event.placemark;
//
//   // prevent scene navigation
//   germ.SetNavigationMode(Germanium.Navigation.Disabled);
//
//   // move placemark along with cursor
//   germ.AddEventHandler(Germanium.Event.OnMouseMove, DragPlacemark);
//}
//
//function DragPlacemark(event)
//{
//   // compute the 3D coordinates at which the mouse button is released
//   var coords = germ.GetEye().Compute3DIntersection(event.windowX, event.windowY);
//
//   // put grabbedPlacemark at the computed coordinates
//   grabbedPlacemark.GetGeometry().SetPosition(coords);
//}

//function DropPlacemark(event)
//{
//   if (grabbedPlacemark != null)
//   {
//      // stop moving placemark along with cursor
//      germ.RemoveEventHandler(Germanium.Event.OnMouseMove, DragPlacemark);
//
//      // compute the 3D coordinates at which the mouse button is released
//      var coords = germ.GetEye().Compute3DIntersection(event.windowX, event.windowY);
//
//      alert(coords)
//
//      // put grabbedPlacemark at the computed coordinates
//      grabbedPlacemark.GetGeometry().SetPosition(coords);
//
//      // reset grabbedPlacemark
//      grabbedPlacemark = null;
//
//      // resume scene navigation
//      germ.SetNavigationMode(Germanium.Navigation.Orbit);
//   }
//}
