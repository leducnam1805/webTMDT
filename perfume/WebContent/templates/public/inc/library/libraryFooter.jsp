<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    
	<!-- JavaScript
================================================== -->
<script src="<%=request.getContextPath() %>/templates/public/js/plugins.js"></script>
<script src="<%=request.getContextPath() %>/templates/public/js/script.js"></script>

<script src="<%=request.getContextPath() %>/templates/public/js/ie10-viewport-bug-workaround.js"></script>


<!-- revolution slider plugin -->

<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>
<!-- sweetalert -->
<script src="<%=request.getContextPath() %>/templates/admin/dist/node_modules/sweetalert2/dist/sweetalert2.all.min.js"></script>

 <script type="text/javascript">

  var revapi;

  jQuery(document).ready(function() {

       revapi = jQuery('.tp-banner').revolution({

        dottedOverlay:"none",
        delay:9000,
        startwidth:1170,
        startheight:700,
        hideThumbs:200,
        
        thumbWidth:100,
        thumbHeight:50,
        thumbAmount:5,
        
        navigationType:"none",
        navigationArrows:"solo",
        navigationStyle:"",
        
        touchenabled:"on",
        onHoverStop:"on",
        
        swipe_velocity: 0.7,
        swipe_min_touches: 1,
        swipe_max_touches: 1,
        drag_block_vertical: false,
                    
                    
        keyboardNavigation:"on",
        
        navigationHAlign:"center",
        navigationVAlign:"bottom",
        navigationHOffset:0,
        navigationVOffset:20,

        soloArrowLeftHalign:"left",
        soloArrowLeftValign:"center",
        soloArrowLeftHOffset:20,
        soloArrowLeftVOffset:0,

        soloArrowRightHalign:"right",
        soloArrowRightValign:"center",
        soloArrowRightHOffset:20,
        soloArrowRightVOffset:0,
            
        shadow:0,
        fullWidth:"on",
        fullScreen:"off",

        spinner:"spinner0",
        
        stopLoop:"off",
        stopAfterLoops:-1,
        stopAtSlide:-1,

        shuffle:"off",
                            
        forceFullWidth:"off",           
        fullScreenAlignForce:"off",           
        minFullScreenHeight:"400",            
                    
        hideThumbsOnMobile:"off",
        hideNavDelayOnMobile:1500,            
        hideBulletsOnMobile:"off",
        hideArrowsOnMobile:"off",
        hideThumbsUnderResolution:0,
        
        hideSliderAtLimit:0,
        hideCaptionAtLimit:0,
        hideAllCaptionAtLilmit:0,
        startWithSlide:0,
        fullScreenOffsetContainer: ""

      });

  }); //ready

</script>