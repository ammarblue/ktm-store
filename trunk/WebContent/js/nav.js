
    jQuery(document).ready(function(){
        //$("ul.subnav").parent().append("<span></span>"); //Only shows drop down trigger when js is enabled (Adds empty span tag after ul.subnav*)
        $("ul#topnav li").click(function() {
            //Following events are applied to the subnav itself (moving subnav up and down)  
            $(this).find("ul.subnav").slideDown('fast').show(); //Drop down the subnav on click
            $(this).hover(
                function() {},
                function(){  
                    $(this).find("ul.subnav").slideUp('fast'); //When the mouse hovers out of the subnav, move it back up  
                }
            );
        });
    });