package cn.galudisu.fp._4_1_proxy_pattern;

/**
 * @author galudisu
 */
public class ActualImage implements Image {

    private final String pathToImageFile;

    public ActualImage(String pathToImageFile) {
        super();
        this.pathToImageFile = pathToImageFile;
    }

    @Override
    public void load() {
        System.out.println("Loading image from file <" + pathToImageFile + ">");
    }

    @Override
    public void renderImage() {
        System.out.println("Rendering image <" + pathToImageFile + ">");
    }
}
