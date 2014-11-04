// Checks for browser compatibility
      if ( !window.requestAnimationFrame ) {
        window.requestAnimationFrame = ( function() {
          return window.webkitRequestAnimationFrame ||
          window.mozRequestAnimationFrame ||
          window.oRequestAnimationFrame ||
          window.msRequestAnimationFrame || 
          function( 
            /* function FrameRequestCallback */ 
            callback, /* DOMElement Element */ element ) {
              window.setTimeout( callback, 1000 / 60 );
            };
        })();
      }

      var Environment = {};

      Environment.init = function() {
        var WIDTH   = window.innerWidth,
            HEIGHT  = window.innerHeight;

        var VIEW_ANGLE  = 45,
            ASPECT      = WIDTH / HEIGHT;
            NEAR        = 0.1,
            FAR         = 10000;

        Environment.renderer = new THREE.WebGLRenderer();

        // Sets up the camera angle and a scene
        Environment.camera = new THREE.PerspectiveCamera( VIEW_ANGLE, ASPECT, NEAR, FAR );
        Environment.scene = new THREE.Scene();

        // the camera starts at 0,0,0 so move it back in the space
        Environment.camera.position.z = 600;
        Environment.scene.add(Environment.camera);

        // start the renderer
        Environment.renderer.setSize( WIDTH, HEIGHT );

        // Attach the render-supplied DOM element
        document.body.appendChild(Environment.renderer.domElement);


        /*----- Create a bounding box supplied to us in three.js ----*/
        var boundingBoxConfig = {
          width: 360,
          height: 360,
          depth: 1300,
          splitX: 6,
          splitY: 6,
          splitZ: 20
        };
        Environment.boundingBoxConfig = boundingBoxConfig;
        Environment.blockSize = boundingBoxConfig.width / boundingBoxConfig.splitX;

        var boundingBox = new THREE.Mesh(
          new THREE.CubeGeometry(
            boundingBoxConfig.width, 
            boundingBoxConfig.height, 
            boundingBoxConfig.depth, 
            boundingBoxConfig.splitX, 
            boundingBoxConfig.splitY, 
            boundingBoxConfig.splitZ
          ), 
          new THREE.MeshLambertMaterial(
            { wireframe: true,
            emissive: new THREE.Color("rgb(255,0,0)") }
          )
        );
        // Add the shape to the scene
        Environment.scene.add(boundingBox);
        // Render Everything
        Environment.renderer.render(Environment.scene, Environment.camera);

        Environment.stats = new Stats();
        Environment.stats.domElement.style.position = 'absolute';
        Environment.stats.domElement.style.top = '10px';
        Environment.stats.domElement.style.left = '10px';
        document.body.appendChild( Environment.stats.domElement );

        document.getElementById("play_button").addEventListener('click', function (event) {
          event.preventDefault();
          Environment.start();
        });
      }

      /**
      * Introduce some time related variables
      * to calculate the blocks forward
      */
      Environment.gameStepTime = 1000;
      Environment.frameTime = 0; // ms
      Environment.cumulatedFrameTime = 0; //ms
      Environment._lastFrameTime = Date.now(); // timestamp

      Environment.gameOver = false;

      Environment.animate = function() {
        var time = Date.now();
        Environment.frameTime = time - Environment._lastFrameTime;
        Environment._lastFrameTime = time;
        Environment.cumulatedFrameTime += Environment.frameTime;

        while ( Environment.cumulatedFrameTime > Environment.gameStepTime ) {
          Environment.cumulatedFrameTime -= Environment.gameStepTime;
        }

        Environment.renderer.render( Environment.scene, Environment.camera );

        Environment.stats.update();

        if ( !Environment.gameOver ) window.requestAnimationFrame(Environment.animate);


      }

      Environment.start = function() {
        document.getElementById("menu").style.display = "none";
        Environment.pointsDOM = document.getElementById("points");
        Environment.pointsDOM.style.display = "block";

        Environment.animate();
      };

      window.addEventListener("load", Environment.init);
