/**
 * Created by IntelliJ IDEA.
 * User: jpbelang
 * Date: 11-09-14
 * Time: 8:17 AM
 * To change this template use File | Settings | File Templates.
 */

if ( window.eventbus == undefined) {

    window.eventbus = {};
    $(document).ready(function() {

        window.eventbus =  {
            subscribe: function(event, fn) {
                $(this).bind(event, fn);
            },
            publish: function(event, data) {
                logging.log("publishing event " + event);
                $(this).trigger(event, data);
            }
        };
    });



}
