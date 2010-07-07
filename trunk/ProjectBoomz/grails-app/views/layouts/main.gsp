<html>
  <head>
    <title><g:layoutTitle default="Project Boomz" /></title>
  <g:layoutHead />
  <script type="text/javascript">
  function darkenButton()
  {
          document.getElementById("textbox_background").id="textbox_background_hover";
  }

  function lightenButton()
  {
          document.getElementById("textbox_background_hover").id="textbox_background";
  }

  function changeIt()
  {
          document.getElementById("right_container").id="right_container_shift";
          document.getElementById("left_container").id="left_container_shift";
  }
  </script>
</head>
<body>
  <div id="container">
    <div id="left_container"></div>
    <div id="right_container">
      <div id="right_container_banner">
        <div id="right_container_banner_left">
        </div>
        <div id="right_container_banner_middle"><img src="boomz.PNG" width="200" height="20"/></div>
        <div id="right_container_banner_right">
        </div>
      </div>
      <div id="right_container_search">
        <div id="right_container_search_left">
        </div>
        <div id="right_container_search_middle">
          <div id="searchHeader">Search : </div>

          <form>
            <div id="textbox_background">
              <input type="text" name="test" id="textbox"/>
              <input type="button" name="test2" id="button" onmouseover="darkenButton()" onmouseout="lightenButton()"/>
            </div>
          </form>
        </div>

        <div id="right_container_search_right">
        </div>
      </div>
      <div id="right_container_content">
        <div id="right_container_content_header">Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!Hi you!</div>
        <div id="right_container_content_content">
          <p>Hi me really hard! Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!Hi me really hard!</p>
          <input type="button" onclick="changeIt()" value="click me"/>
        </div>
      </div>
    </div>
  </div>
  <g:layoutBody/>
</body>
</html>