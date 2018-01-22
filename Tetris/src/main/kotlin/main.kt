import org.w3c.dom.events.Event
import three.cameras.Camera
import three.cameras.PerspectiveCamera
import three.geometries.BoxGeometry
import three.geometries.PlaneGeometry
import three.geometries.SphereGeometry
import three.lights.DirectionalLight
import three.loaders.TextureLoader
import three.materials.basic.MeshBasicMaterial
import three.materials.basic.MeshBasicMaterialParam
import three.materials.meshlambert.MeshLambertMaterial
import three.materials.meshlambert.MeshLambertMaterialParam
import three.math.Color
import three.objects.Mesh
import three.renderers.webglrenderer.WebGLRenderer
import three.scenes.Scene
import kotlin.browser.document
import kotlin.browser.window
import kotlin.math.PI


fun main(args: Array<String>){
    //window.addEventListener("load",::init)
}

/*fun init(e: Event){

    val renderer = WebGLRenderer()
    renderer.setPixelRatio(window.devicePixelRatio)
    renderer.setSize(window.innerWidth, window.innerHeight)

    val scene = Scene()

    val camera = PerspectiveCamera(45, window.innerWidth / window.innerHeight ,0.1 , 1000)
    camera.position.set(0f,0f,1000f)

    val geometry = SphereGeometry(300,30,30)

    val param = MeshLambertMaterialParam()
    param.color = 0xff0000
    val material = MeshLambertMaterial(param)
    val box = Mesh(geometry,material)

    scene.add(box)

    val directionalLight = DirectionalLight(0xffffff)
    directionalLight.position.set(1f,1f,1f)

    scene.add(directionalLight)

    document.getElementById("WebGL-output")!!
            .appendChild(renderer.domElement)

    fun tick(d : Double){
        box.rotation.y += 0.01
        renderer.render(scene, camera)

        window.requestAnimationFrame(::tick)
    }

    tick(0.toDouble())

    console.log("Render done.")
}*/