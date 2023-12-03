# my

每天没事积积功德

attribute vec4 a_position;
attribute vec2 a_texCoord0;
attribute vec2 a_texCoord1;
attribute vec4 a_color;
uniform mat4 u_projTrans;
varying vec4 v_texCoords;
varying vec4 v_color;
void main() {
    v_texCoords.xy = a_texCoord0;
    v_texCoords.zw = a_texCoord1;\n    
v_color = a_color;\n    
v_color.a = v_color.a * (255.0/254.0);\n
gl_Position = u_projTrans * a_position;
\n}

#ifdef GL_ES\n
#define LOWP lowp\n    
precision mediump float;\n#else\n    
#define LOWP \n#endif\n
varying vec4 v_texCoords;\n
varying LOWP vec4 v_color;\n
uniform sampler2D u_pic;\n
uniform sampler2D u_mask;\n\n
void main() {\n    
LOWP vec4 pic = texture2D(u_pic, v_texCoords.xy);\n    
LOWP vec4 msk = texture2D(u_mask, v_texCoords.zw);\n   
float ratio = (1.0 - msk.g) * msk.r;\n    
LOWP vec3 masked = pic.rgb * vec3(ratio) + msk.ggg;\n    
gl_FragColor.a = msk.a * v_color.a;\n    
gl_FragColor.rgb = masked * vec3(v_color.r + 1.0);
}