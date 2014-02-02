if (window.logging == undefined) {

    window.logging = {
            log: function(fmt, args) {

                if(window.console && window.console.log) {
                    if ( args ) {
                        console.log(fmt, args);
                    } else {

                        console.log(fmt);
                    }
                }
            }
    };

    jQuery.fn.log = function (msg) {

        window.logging.log("%s: %o", msg, this);

        return this;
    }
}