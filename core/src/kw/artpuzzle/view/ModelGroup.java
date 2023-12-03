package kw.artpuzzle.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kw.gdx.asset.Asset;

/**
 * @Auther jian xian si qi
 * @Date 2023/5/16 20:07
 *
 * 基本就是这样弄了，  具体细节用的时候在完善
 */
public class ModelGroup  extends Image {
    private ShaderProgram program;
    private Texture t;
    private float startU = 0.f;
    private float startV = 0.f;
    private float u;
    private float v;

    public ModelGroup(String maskName){
        super(Asset.getAsset().getTexture(maskName));
        program = new ShaderProgram(
                Gdx.files.internal("shader/masktest.vert"),
                Gdx.files.internal("shader/masktest.frag"));
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        t = Asset.getAsset().getTexture("animals_426.jpg");
        u = t.getWidth() / getWidth() ;
        v = t.getWidth() / getWidth() ;
        t.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setShader(program);
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE1);
        t.bind();
        int uniformLocation = program.getUniformLocation("u_texture3");
        program.setUniformi(uniformLocation,1);
        System.out.println(startU);
        program.setUniformf("u",startU);
        program.setUniformf("u2",startU+u);
        program.setUniformf("v",startV);
        program.setUniformf("v2",startV+v);
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        super.draw(batch, parentAlpha);
        batch.setShader(null);
    }

    public void setStartU(float startU) {
        this.startU = startU;
    }

    public void setStartV(float startV) {
        this.startV = startV;
    }

    private float posX;
    public void setPosX(float x) {
        this.posX = x;
    }

    public float posY;
    public void setPosY(float y) {
        this.posY = y;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
}