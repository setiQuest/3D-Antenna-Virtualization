package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;

import com.jme3.terrain.heightmap.RawHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jme3tools.converters.ImageToAwt;
import org.codehaus.jackson.map.ObjectMapper;



/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    DataGetter getter;
    ObjectMapper mapper;
    ResultClass results;
    
    // for drawing the terrain
    private TerrainQuad terrain;
    AbstractHeightMap heightmap = null;
    Texture heightMapImage = null;
    Material matRock;
    Material matWire;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        try {
            String[] testLocations = new String[4];
            testLocations[0] = "42.279552,-83.06967"; // four corners of Malden Park
            testLocations[1] = "42.275774,-83.070986";
            testLocations[2] = "42.27752,-83.059421";
            testLocations[3] = "42.273551,-83.059721";
            
            getter = new DataGetter(testLocations);
            getter.retrieveData();
            mapper = new ObjectMapper();
            
            //results = mapper.readValue(new File("d:\\tempfiles\\results.json"), ResultClass.class);
            //results = mapper.readValue();
            // getter.getElevationData();
            
            results = mapper.readValue(getter.getElevationData(), ResultClass.class);
            guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
            
            BitmapText helloText = new BitmapText(guiFont, false);
            helloText.setSize(guiFont.getCharSet().getRenderedSize());
            
            helloText.setText(results.getText());
            helloText.setLocalTranslation(300, helloText.getLineHeight(), 0);
            guiNode.attachChild(helloText);
            
            // prepare and draw the terrain...
            matRock = new Material(assetManager, "Common/MatDefs/Terrain/Terrain.j3md");
            matRock.setTexture("Alpha", assetManager.loadTexture("Textures/Terrain/splat/alphamap.png"));
            Texture grass = assetManager.loadTexture("Textures/Terrain/splat/grass.jpg");
            grass.setWrap(WrapMode.Repeat);
            matRock.setTexture("m_Tex1", grass);
            matRock.setFloat("m_Tex1Scale", 64f);
                Texture dirt = assetManager.loadTexture("Textures/Terrain/splat/dirt.jpg");
    dirt.setWrap(WrapMode.Repeat);
    matRock.setTexture("m_Tex2", dirt);
    matRock.setFloat("m_Tex2Scale", 32f);
    Texture rock = assetManager.loadTexture("Textures/Terrain/splat/road.jpg");
    rock.setWrap(WrapMode.Repeat);
    matRock.setTexture("m_Tex3", rock);
    matRock.setFloat("m_Tex3Scale", 128f);
            //terrain = new TerrainQuad("Windsor", 65, 513, results.D_getRawHeightMap());
            heightMapImage = assetManager.loadTexture("Textures/ireland.png");
            heightmap = new ImageBasedHeightMap(ImageToAwt.convert(heightMapImage.getImage(), false, true, 0), 2f);
            heightmap.load();
            terrain = new TerrainQuad("Windsor", 65, 1025, heightmap.getHeightMap());
            terrain.setMaterial(matRock);
            terrain.setModelBound(new BoundingBox());
            // terrain.updateModelBound();
            terrain.setLocalTranslation(0, -100, 0);
            terrain.setLocalScale(2f, 1f, 2f);
            rootNode.attachChild(terrain);
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
