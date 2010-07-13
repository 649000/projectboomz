<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample title</title>
    <script src="http://api.germanium3d.com/?v=1.4&key=0c1db0e05cd88587a664a659962b25c0"></script>
  <g:javascript library="scriptaculous" />
  <g:javascript library="prototype" />
  <script type="text/javascript" src="${resource(dir: 'js', file: 'reportscript.js')}" ></script>
  <script src="http://maps.google.com/maps?file=api&amp;v=3&amp;key=ABQIAAAAl3XLeSqUNe8Ev9bdkkHWFBTlogEOPz-D7BlWWD22Bqn0kvQxhBQR-
          srLJJlcXUmLMTM2KkMsePdU1A"
  type="text/javascript"></script>
</head>

<body onLoad="${remoteFunction(action:'loadData',onSuccess:'Init(e)')}" >
  <div id="map" style="width: 100%; height: 600px;"></div>
</body>
</html>
