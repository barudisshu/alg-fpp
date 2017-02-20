package cn.galudisu.fp._4_1_proxy_pattern;

import org.junit.Test;

/**
 * @author galudisu
 */
public class ImageTest {

    Image image = new Thumbnail("/path/to/image");

    @Test
    public void load() throws Exception {
        image.load();
    }

    @Test
    public void renderImage() throws Exception {
        image.renderImage();
    }

}