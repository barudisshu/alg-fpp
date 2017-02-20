package cn.galudisu.fp._4_1_proxy_pattern;

/**
 * 代理类，拥有真实类的方法，但处理过程传递给真实对象
 *
 * @author galudisu
 */
public class Thumbnail implements Image {

    private final String pathToImage;
    private final ActualImage actualImage;
    private boolean thumbNailLoaded;

    public Thumbnail(String pathToImage) {
        super();
        this.pathToImage = pathToImage;
        this.thumbNailLoaded = false;
        this.actualImage = new ActualImage(pathToImage);
    }

    @Override
    public void load() {
        System.out.println("Loading Thumb Nail <" + pathToImage + ">");
    }

    @Override
    public void renderImage() {
        if (!thumbNailLoaded) { // 4
            load();
            thumbNailLoaded = true;
            System.out.println("Render the thumb nail");
        } else {
            actualImage.load(); // 5
            actualImage.renderImage();
        }
    }
}
