if ( window.boot == undefined ) {


    window.boot = {};

    $(document).ready(function() {

        window.boot.bootinfo = new Message();

        window.boot.ready = function(callback) {

            if ( boot.bootinfo.links == null ) {
                boot.bootinfo.restLoad({
                    url: "/rest/boot",
                    success: callback
                });
            } else {

                callback(boot.bootinfo);
            }

        }
    })
}