/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function checkLogin(response)
{
    var nric = $F("nric");
    var password = $F("password");
    var temp = response.responseText

    if(nric != '' || password != '')
        {
        if (temp =="Success")
    {
        alert("Success")
        //redirect somewhere lah or use remote fuction and call other method lah
        //${remoteFunction(controller:'report', action:'index')}
       
    }
    else if (temp == "Invalid Password")
    {
        alert("Invalid Password")
    
    } else if (temp == "Invalid Login ID")
    {
        alert("Invalid Login ID")
    }
        }
        else
            alert("Login ID or Password Cannot be blank")
}
