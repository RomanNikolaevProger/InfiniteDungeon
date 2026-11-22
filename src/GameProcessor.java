import java.io.FileNotFoundException;

public class GameProcessor {
    int StageID = 0;
    int Difficulty = 0;
    int GameMode = 0;
    int Class = 0;
    boolean PressedA;
    boolean PressedD;
    boolean PressedS;
    boolean PressedW;
    int Room = 7;
    int PlayerX = 980;
    int PlayerY = 540;
    int Speed = 10;
    int[][] Tiles = new int[29][17];

    RoomReader Rooms = new RoomReader();

    public int[][] getTiles() {
        return(Tiles);
    }
    public void SetTiles() {
        try {
            System.arraycopy(Rooms.ReadRoom(Room), 0, Tiles, 0, 17);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void tick() {
        if (StageID==-1) {
            if (PlayerX > 1824) {
                PlayerX = 32;
                Room++;
                SetTiles();
            }
            if (PlayerX < 32) {
                PlayerX = 1824;
                Room--;
                SetTiles();
            }
            if (PlayerY > 1056) {
                PlayerY = 32;
            }
            if (PlayerY < 32) {
                PlayerY = 1056;
            }
            if (PressedA) {
                /*if (Tiles[(PlayerY+12)/64][(PlayerX-18-Speed)/64]!=2 || Tiles[(PlayerY-12)/64][(PlayerX-18-Speed)/64]!=2) {
                    PlayerX = (PlayerX-18-Speed)/64*64+19+64;
                } else {
                    PlayerX -= Speed;
                }*/
                PlayerX -= Speed;
            }
            if (PressedD) {
                /*if (Tiles[(PlayerY+12)/64][(PlayerX+18+Speed)/64]!=2 || Tiles[(PlayerY-12)/64][(PlayerX+18+Speed)/64]!=2) {
                    PlayerX = (PlayerX+18+Speed)/64*64-19;
                } else {
                    PlayerX += Speed;
                }*/
                PlayerX += Speed;
            }
            if (PressedS) {
                /*if (Tiles[(PlayerY+12+Speed)/64][(PlayerX+18)/64]!=2 || Tiles[(PlayerY+12+Speed)/64][(PlayerX-18)/64]!=2) {
                    PlayerY = (PlayerY+12+Speed)/64*64-13;
                } else {
                    PlayerY += Speed;
                }*/
                PlayerY += Speed;
            }
            if (PressedW) {
                /* if (Tiles[(PlayerY-12-Speed)/64][(PlayerX+18)/64]!=2 || Tiles[(PlayerY-12-Speed)/64][(PlayerX-18)/64]!=2) {
                    PlayerY = (PlayerY-12-Speed)/64*64+13+64;
                } else {
                    PlayerY -= Speed;
                }*/
                PlayerY -= Speed;
            }
        }
    }
}
