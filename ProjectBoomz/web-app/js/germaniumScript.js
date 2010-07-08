

var germ=null, currClipPlane=null, LastExpand=true;
function Init()
{
    Germanium.CreateInstance("myGerm", InitSuccessCallback, InitFailureCallback);
    
}

function InitSuccessCallback(obj)
{
    germ = obj;
    germ.Load("http://www.germanium3d.com/static/sample/generic_building/generic_building.xlcl", null, LoadOk, LoadFail);
}

// Will be called if loading finishes successfully
//function LoadOk() {
//    alert('Loading successful!');
//}

function InitFailureCallback(message, code)
{
	alert("Germanium initialization failed: " + message + "; error code: " + code);
}

// Will be called if loading finishes successfully
function LoadOk()
{
	//alert('Loading successful!');
}

// Will be called if loading fails
function LoadFail(errMsg, errCode)
{
	alert('Loading failed. Error code: ' + errCode + '; message: ' + errMsg);
}

// Load a building file
function LoadBuilding()
{
germ.Load("http://www.germanium3d.com/static/sample/generic_building/generic_building.xlcl", null, LoadOk, LoadFail);
}