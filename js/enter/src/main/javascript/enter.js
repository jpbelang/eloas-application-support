if (window.enter == undefined) {


    jQuery.fn.enterKey = function (callback) {
        this.keyup(function (e) {
                    if (e.keyCode == 13) {
                        e.preventDefault();
                        if (typeof callback == 'function')
                            return callback.apply(this);
                    }
                }
        );
        return this;
    }
}
