if (window.basicoperations == undefined) {

    window.basicopertaions = {};

    function mergeObject(object, xhr, done) {
        object.payload = xhr;
        if (done != null) {

            done(object);
        }
    }

    function Message(object) {

        this.payload = object;
    }

    Message.prototype.restLoad = function (info) {

        var self = this;
        $.ajax({
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json"
            },
            type: 'GET',
            url: (typeof info.url == "string")? info.url:info.url.URL,
            success: function (xhr) {

                mergeObject(self, xhr, info.success);

            },
            cache: false,
            error: info.failure
        })
    };

    Message.prototype.restReload = function (callbacks) {

        $.ajax({
            headers: {
                Accept : "application/json",
                "Content-Type": "application/json"
            },
            type: 'GET',
            url: this.restLink("self").URL,
            success:function (xhr) {

                mergeObject(self, xhr, callbacks.success);

            },
            cache: false,
            error: callbacks.failure
        })
    };

    Message.prototype.restCreate = function (callbacks) {

        var self = this;
        $.ajax({
            headers: {
                Accept : "application/json",
                "Content-Type": "application/json"
            },
            type: 'POST',
            data: $.toJSON(self.payload),
            url: callbacks.url.URL,
            success: function(xhr) {

                mergeObject(self, xhr, callbacks.success);
            },
            error: callbacks.failure
        })
    }

    Message.prototype.restUpdate = function(callbacks) {

        var self = this;
        $.ajax({
            headers: {
                Accept : "application/json",
                "Content-Type": "application/json"
            },
            type: 'PUT',
            data: $.toJSON(self.payload),
            url:  this.restLink("self").URL,
            success: callbacks.success,
            error: callbacks.failure
        })    
    };
    
    Message.prototype.restPut = function(callbacks) {

        var self = this;
        $.ajax({
            headers: {
                Accept : "application/json",
                "Content-Type": "application/json"
            },
            type: 'PUT',
            data: $.toJSON(self.payload),
            url:  callbacks.url.URL,
            success: function(xhr) {

                mergeObject(self, xhr, callbacks.success);
            },
            error: callbacks.failure
        })
    }
    
    Message.prototype.restRemove = function(callbacks) {

        $.ajax({
            headers: {
                Accept : "application/json",
                "Content-Type": "application/json"
            },
            type: 'DELETE',
            url:  this.restLink("self").location,
            success: callbacks.success,
            error: callbacks.failure
        })
    }
    
    
    
    
    
    Message.prototype.restLink = function(name) {

            for(var i=0;i<this.payload.links.length;i++)
            {
                var link = this.payload.links[i];
                if(name == link.name){
                    return link;
                }
            }

            throw "structure has no link named " + name;
    };
}