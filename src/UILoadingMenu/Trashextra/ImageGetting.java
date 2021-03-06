package UILoadingMenu.Trashextra;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * http://redthirddivision.com/team/blp"> Matthew Rogers</a>
 */
public class ImageGetting {

    private final static Map<String, BufferedImage> texMap = new HashMap<String, BufferedImage>();

    private BufferedImage image;
    private String        fileName;
    private int           width, height;

    public ImageGetting(String fileName) {
        this.fileName = fileName;
        BufferedImage oldTexture = texMap.get(fileName);
        if (oldTexture != null)
            this.image = oldTexture;
        else {
            try {
                System.out.println("Loading texture: " + fileName);
                this.image = ImageIO.read(new File("./res/" + fileName + ".png"));
                texMap.put(fileName, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public ImageGetting(ImageGetting spriteSheet, int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        String key = spriteSheet.fileName + "_" + x + "_" + y;
        BufferedImage old = texMap.get(key);
        if (old != null) this.image = old;
        else this.image = spriteSheet.image.getSubimage(
                x * width - width,
                y * height - height,
                width, height);
    }

    public ImageGetting(ImageGetting spriteSheet, int x, int y, int size) {
        this(spriteSheet, x, y, size, size);
    }

    public void render(Graphics2D g, double x, double y) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public void render(Graphics2D g, int destX1, int destX2, int srcX1, int srcX2, int y) {
        g.drawImage(image, destX1, y, destX2, y + height, srcX1, 0, srcX2, height, null);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        return image;
    }

}