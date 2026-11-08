import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Painter extends JPanel{


    Image BackGround = ImageIO.read(new File("src/assets/textures/gui/MenuBackground.png"));
    Image ExitButton = ImageIO.read(new File("src/assets/textures/gui/ExitButton.png"));
    Image PlayButton = ImageIO.read(new File("src/assets/textures/gui/PlayButton.png"));
    // Image SettingsButton = ImageIO.read(new File("src/assets/textures/gui/SettingsButton.png"));
    Image Difficulty0 = ImageIO.read(new File("src/assets/textures/gui/Difficulty0.png"));
    Image Difficulty0s = ImageIO.read(new File("src/assets/textures/gui/Difficulty0s.png"));
    Image Difficulty1 = ImageIO.read(new File("src/assets/textures/gui/Difficulty1.png"));
    Image Difficulty1s = ImageIO.read(new File("src/assets/textures/gui/Difficulty1s.png"));
    Image Difficulty2 = ImageIO.read(new File("src/assets/textures/gui/Difficulty2.png"));
    Image Difficulty2s = ImageIO.read(new File("src/assets/textures/gui/Difficulty2s.png"));
    Image ClassMelee = ImageIO.read(new File("src/assets/textures/gui/ClassMelee.png"));
    Image ClassRanged = ImageIO.read(new File("src/assets/textures/gui/ClassRanged.png"));
    Image ClassMagic = ImageIO.read(new File("src/assets/textures/gui/ClassMage.png"));
    Image ClassMelees = ImageIO.read(new File("src/assets/textures/gui/ClassMelees.png"));
    Image ClassRangeds = ImageIO.read(new File("src/assets/textures/gui/ClassRangeds.png"));
    Image ClassMagics = ImageIO.read(new File("src/assets/textures/gui/ClassMages.png"));
    Image Block = ImageIO.read(new File("src/assets/textures/tiles/Block.png"));
    Image Wall = ImageIO.read(new File("src/assets/textures/tiles/Wall.png"));
    Image Floor = ImageIO.read(new File("src/assets/textures/tiles/Floor.png"));
    Image WallTopDown = ImageIO.read(new File("src/assets/textures/tiles/WallTopDown.png"));
    Image WallTopLeft = ImageIO.read(new File("src/assets/textures/tiles/WallTopLeft.png"));
    Image WallTopUp = ImageIO.read(new File("src/assets/textures/tiles/WallTopUp.png"));
    Image WallTopRight = ImageIO.read(new File("src/assets/textures/tiles/WallTopRight.png"));
    Image WallTopLeftDown = ImageIO.read(new File("src/assets/textures/tiles/WallTopLeftDown.png"));
    Image WallTopLeftUp = ImageIO.read(new File("src/assets/textures/tiles/WallTopLeftUp.png"));
    Image WallTopRightDown = ImageIO.read(new File("src/assets/textures/tiles/WallTopRightDown.png"));
    Image WallTopRightUp = ImageIO.read(new File("src/assets/textures/tiles/WallTopRightUp.png"));
    Image[] TileImages = {Block,Wall,Floor,WallTopDown,WallTopLeft,WallTopUp,WallTopRight,WallTopLeftDown,WallTopLeftUp,WallTopRightDown,WallTopRightUp};
    Image PlayerStand = ImageIO.read(new File("src/assets/textures/entities/PlayerStand.png"));


    GameProcessor GP;
    GameWindow painter;

    public Painter(GameProcessor processor, GameWindow GW) throws IOException {
        this.GP = processor;

    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        try {
            if (GP.StageID==0) {
                g.drawImage(BackGround, 0, 0, null);
                g.drawImage(ExitButton, 864, 705, null);
                g.drawImage(PlayButton, 864, 305, null);
                //g.drawImage(SettingsButton, 735, 505, null);
            }
            if (GP.StageID==1) {
                g.drawImage(BackGround, 0, 0, null);
                g.drawImage(ExitButton, 700, 705, null);
                g.drawImage(PlayButton, 1028, 705, null);
                if (GP.Difficulty == 0) {
                    g.drawImage(Difficulty0s, 664, 505, null);
                } else {
                    g.drawImage(Difficulty0, 664, 505, null);
                }
                if (GP.Difficulty == 1) {
                    g.drawImage(Difficulty1s, 864, 505, null);
                } else {
                    g.drawImage(Difficulty1, 864, 505, null);
                }
                if (GP.Difficulty == 2) {
                    g.drawImage(Difficulty2s, 1064, 505, null);
                } else {
                    g.drawImage(Difficulty2, 1064, 505, null);
                }
                if (GP.Class == 0) {
                    g.drawImage(ClassMelees, 600, 305, null);
                } else {
                    g.drawImage(ClassMelee, 600, 305, null);
                }
                if (GP.Class == 1) {
                    g.drawImage(ClassRangeds, 850, 305, null);
                } else {
                    g.drawImage(ClassRanged, 850, 305, null);
                }
                if (GP.Class == 2) {
                    g.drawImage(ClassMagics, 1100, 305, null);
                } else {
                    g.drawImage(ClassMagic, 1100, 305, null);
                }
            }
            if (GP.StageID == -1) {
                int[][] Tiles = GP.getTiles();
                for (int i = 0; i < 29; i++) {
                    for (int j = 0; j < 17; j++) {
                        g.drawImage(TileImages[Tiles[j][i]],i*64,j*64,null);
                    }
                }
                g.drawImage(PlayerStand,GP.PlayerX-26,GP.PlayerY-56,null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
