Messages = Class.extend({
    handler: {},

    init: function () {
        this.handler['Pawn'] = this.handlePawn;
        this.handler['Bomb'] = this.handleBomb;
        this.handler['Box'] = this.handleBox;
        this.handler['Fire'] = this.handleFire;
        this.handler['Buff'] = this.handleBonus;
    },

    move: function (direction) {
        var template = {
            topic: "MOVE",
            data: {}
        };

        template.data.direction = direction.toUpperCase();
        template.data.possess = gInputEngine.possessed;
        return JSON.stringify(template);
    },

    plantBomb: function () {
        var template = {
            topic: "PLANT_BOMB",
            data: gInputEngine.possessed
        };

        return JSON.stringify(template);
    },


    handleReplica: function (msg) {
        var data = JSON.parse(msg.data);
        var gameObjects = data.objects;
        var survivors = new Set();

        for (var i = 0; i < gameObjects.length; i++) {
            var obj = gameObjects[i];
            if (gMessages.handler[obj.type] === undefined)
                continue;

            survivors.add(obj.id);
            gMessages.handler[obj.type](obj);
        }
        gGameEngine.gc(survivors);
    },

    handlePossess: function (msg) {
        var data = JSON.parse(msg.data);
        gInputEngine.possessed = parseInt(data.possess);
        gGameEngine.playersCount = parseInt(data.playersCount);
    },

    handleEndMatch: function (msg) {
        var data = JSON.parse(msg.data);
        if (data.hasWinner === true) {
            console.log('has winner');
            if (gInputEngine.possessed === data.possess) {
                gGameEngine.gameOver('win');
                console.log('win');
                return;
            }
        }
        gGameEngine.gameOver('lose');
        console.log('lose');
    },

    handlePawn: function(obj) {
        var player = gGameEngine.players.find(function (el) {
            return el.id === obj.id;
        });
        var position = Utils.getEntityPosition(obj.position);

        if (player) {
            player.bmp.x = position.x;
            player.bmp.y = position.y;
            if (obj.alive === false) {
                console.log('alive == false');
                player.die();
                if (obj.id === gInputEngine.possessed) {
                    gGameEngine.gameOver('lose');
                }
            }
        } else {
            console.log(new Date().getTime() + " handel new player " + obj.id);
            player = new Player(obj.id, position);
            gGameEngine.players.push(player);
        }
    },

    handleBomb: function(obj) {
        var bomb = gGameEngine.bombs.find(function (el) {
            return el.id === obj.id;
        });
        var position = {};
        position.x = obj.position.x + 6;
        position.y = -obj.position.y + 12 * 33 - 6;
        if (!bomb) {
            bomb = new Bomb(obj.id, position);
            gGameEngine.bombs.push(bomb);
        }
    },

    handleBox: function (obj) {
        var box = gGameEngine.boxes.find(function (el) {
            return el.id === obj.id;
        });

        var position = Utils.getEntityPosition(obj.position);

        if (!box) {
            gGameEngine.boxes.push(new Box(obj.id, position));
        }
    },

    handleFire: function (obj) {
        var fire = gGameEngine.fires.find(function (el) {
            return el.id === obj.id;
        });

        var position = Utils.getEntityPosition(obj.position);
        if (!fire) {
            fire = new Fire(obj.id, position);
            gGameEngine.fires.push(fire);
        }
    },

    handleBonus: function (obj) {
        var bonus = gGameEngine.bonuses.find(function (el) {
            return el.id === obj.id;
        });
        var types = ['speed', 'capacity', 'power']
        var position = Utils.getEntityPosition(obj.position);
        var typePosition = types.findIndex(s => s == obj.buffType.toLowerCase())
        if (bonus) {
            bonus.type = types[typePosition];
        } else {
            bonus = new Bonus(obj.id, position, typePosition);
            gGameEngine.bonuses.push(bonus);
        }
    }

});

gMessages = new Messages();