<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>


    <script type="text/javascript">
	function darkenButton()
	{
		document.getElementById("textbox").className="textbox_background_hover";
	}

	function lightenButton()
	{
		document.getElementById("textbox").className="textbox_background";
	}

	function changeIt()
	{
		document.getElementById("right_container").className="right_container_shift";
		document.getElementById("map").className="left_container_shift";
	}
	</script>


        <g:javascript library="scriptaculous" />

        <script src="http://maps.google.com/maps?file=api&amp;v=3&amp;key=ABQIAAAAl3XLeSqUNe8Ev9bdkkHWFBTlogEOPz-D7BlWWD22Bqn0kvQxhBQR-srLJJlcXUmLMTM2KkMsePdU1A"
            type="text/javascript"></script>

    <script type="text/javascript" src="${resource(dir: 'js', file: 'scripts.js')}" ></script>

<link rel="stylesheet" href="${resource(dir:'css', file:'main.css')}" />
</head>

  <body onload="load()" onunload="GUnload()">
    <div class="container">
      <div id="map" class="left_container"></div>
      <div id="right_container" class="right_container">
        <div class="right_container_banner">
          <div class="right_container_banner_left">
          </div>
          <div class="right_container_banner_middle"> <img src="${resource(dir:'images',file:'boomz.PNG')}" /></div>
          <div class="right_container_banner_right">
          </div>
        </div>
        <div class="right_container_search">
          <div class="right_container_search_left">
          </div>
          <div class="right_container_search_middle">
          <div class="searchHeader">Search : </div>

          <div id="textbox" class="textbox_background">
          
          <g:form>

            <resource:autoComplete skin="default" />
            <g:form>
              <richui:autoComplete name="keyword" class="textbox" action="${createLinkTo('dir': 'building/autoComplete')}" />
            </g:form>
            <span  onmouseover="darkenButton()" onmouseout="lightenButton()">
            <g:submitToRemote
            url="[controller: 'building', action:'search']"
            onSuccess="load(e)"
            onLoading=""
            onComplete=""
            class="button" />
            </span>
          </g:form>
          </div>
          </div>

          <div class="right_container_search_right">
          </div>
        </div>
        <div class="right_container_content">
          <div class="right_container_content_header">Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!</div>
          <div class="right_container_content_content">
            <p>Hi me really hard! Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!
            </p>
            <input type="button" onclick="changeIt()" value="click me"/>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
