Box = Entity.extend({
    /**
    * Entity position on map grid
    */
    position: {},

    /**
    * Bitmap dimensions
    */
    size: {
      w: 32,
      h: 32
    },

    /**
    * Bitmap animation
    */
    bmp: null,

    material: '',

    init: function(id, position) {
        this.id = id;
        this.position = position;
        this.bmp = new createjs.Bitmap(gGameEngine.tilesImgs.wood);

        this.bmp.x = position.x;
        this.bmp.y = position.y;

        gGameEngine.stage.addChild(this.bmp);
    },

    update: function() {
    },

    remove: function() {
      gGameEngine.stage.removeChild(this.bmp);
    }
});