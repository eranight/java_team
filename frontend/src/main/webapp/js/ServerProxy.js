ServerProxy = Class.extend({
    gameServerUrl: "localhost:8090",
    matchMakerUrl: "http://localhost:8080/matchmaker/join",
    matchMakerSignUpUrl: "http://localhost:8080/matchmaker/signup",
	gameId: "1234",
	currentState: "offline",
	
    socket: null,

    handler: {},

    init: function () {
        this.handler['REPLICA'] = gMessages.handleReplica;
        this.handler['POSSESS'] = gMessages.handlePossess;

        var self = this;
        gInputEngine.subscribe('up', function () {
            self.socket.send(gMessages.move('up'))
        });
        gInputEngine.subscribe('down', function () {
            self.socket.send(gMessages.move('down'))
        });
        gInputEngine.subscribe('left', function () {
            self.socket.send(gMessages.move('left'))
        });
        gInputEngine.subscribe('right', function () {
            self.socket.send(gMessages.move('right'))
        });
        gInputEngine.subscribe('bomb', function () {
            self.socket.send(gMessages.plantBomb())
        });
    },

	registryFromMatchMaker: function () {
		var that = this;
        var login = $("#popreglog").val();
		var password = $("#popregpass1").val();
		$.ajax({
            contentType: 'application/x-www-form-urlencoded',
            data: "login=" + login + "&password=" + password,
            dataType: 'text',
            success: function(data){
                console.log("Matchmaker registry");
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert(jqXHR.responseText);
                console.log(jqXHR.responseText);
            },
            processData: false,
            type: 'POST',
            url: that.matchMakerSignUpUrl
        });
	},
	
    getSessionIdFromMatchMaker: function () {
        var that = this;
        var login = $("#popinlog").val();
		var password = $("#popinpass").val();
        $.ajax({
            contentType: 'application/x-www-form-urlencoded',
            data: "login=" + login + "&password=" + password,
            dataType: 'text',
            success: function(data){
                that.gameId=data;
                console.log("Matchmaker returned gameId=" + data);
                that.connectToGameServer(that.gameId, login);
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert(jqXHR.responseText);
                console.log(jqXHR.responseText);
                console.log(errorThrown);
            },
            processData: false,
            type: 'POST',
            url: that.matchMakerUrl
        });
    },
	
    connectToGameServer: function (gameId, login) {
        var self = this;
        this.socket = new WebSocket("ws://" + this.gameServerUrl + "/game/connect?gameId=" + gameId + "&name=" + login);

        this.socket.onopen = function () {
            console.log("Connection established.");
        };

        this.socket.onclose = function (event) {
            if (event.wasClean) {
                console.log('closed');
            } else {
                console.log('alert close');
            }
            console.log('Code: ' + event.code + ' cause: ' + event.reason);
        };

        this.socket.onmessage = function (event) {
            var msg = JSON.parse(event.data);
            if (self.handler[msg.topic] === undefined)
                return;

            self.handler[msg.topic](msg);
        };

        this.socket.onerror = function (error) {
            console.log("Error " + error.message);
        };
    }

});

serverProxy = new ServerProxy();