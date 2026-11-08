public class GameProcessor {
    int StageID = 0;
    int Difficulty = 0;
    int GameMode = 0;
    int Class = 0;
    boolean PressedA;
    boolean PressedD;
    boolean PressedS;
    boolean PressedW;
    int[][][] RoomCodes = {{
            {0,0,0,0,3,3,3,3,3,3,3,3,9,2,2,2,7,3,3,3,3,3,3,3,3,0,0,0,0},
            {0,0,0,6,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,4,0,0,0},
            {0,0,0,6,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,0,0,0},
            {0,0,0,6,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,0,0,0},
            {0,0,0,6,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,0,0,0},
            {3,3,3,9,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,7,3,3,3},
            {1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
            {5,5,5,10,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,8,5,5,5},
            {0,0,0,6,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,0,0,0},
            {0,0,0,6,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,0,0,0},
            {0,0,0,6,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,0,0,0},
            {0,0,0,6,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,0,0,0},
            {0,0,0,0,5,5,5,5,5,5,5,5,10,2,2,2,8,5,5,5,5,5,5,5,5,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,6,2,2,2,4,0,0,0,0,0,0,0,0,0,0,0,0}

    },
    };
    int[][] test = {{},{}};
    int Room = 0;
    int PlayerX = 980;
    int PlayerY = 540;
    int Speed = 10;
    int[][] Tiles = new int[29][17];
    public int[][] getTiles() {
        SetTiles();
        return(Tiles);
    }
    public void SetTiles() {
        System.arraycopy(RoomCodes[Room], 0, Tiles, 0, 17);
    }
    public void tick() {
        if (StageID==-1) {
            if (PlayerX > 1824) {
                PlayerX = 32;
            }
            if (PlayerX < 32) {
                PlayerX = 1824;
            }
            if (PlayerY > 1056) {
                PlayerY = 32;
            }
            if (PlayerY < 32) {
                PlayerY = 1056;
            }
            if (PressedA) {
                if (Tiles[(PlayerY+12)/64][(PlayerX-18-Speed)/64]!=2 || Tiles[(PlayerY-12)/64][(PlayerX-18-Speed)/64]!=2) {
                    PlayerX = (PlayerX-18-Speed)/64*64+19+64;
                } else {
                    PlayerX -= Speed;
                }
            }
            if (PressedD) {
                if (Tiles[(PlayerY+12)/64][(PlayerX+18+Speed)/64]!=2 || Tiles[(PlayerY-12)/64][(PlayerX+18+Speed)/64]!=2) {
                    PlayerX = (PlayerX+18+Speed)/64*64-19;
                } else {
                    PlayerX += Speed;
                }
            }
            if (PressedS) {
                if (Tiles[(PlayerY+12+Speed)/64][(PlayerX+18)/64]!=2 || Tiles[(PlayerY+12+Speed)/64][(PlayerX-18)/64]!=2) {
                    PlayerY = (PlayerY+12+Speed)/64*64-13;
                } else {
                    PlayerY += Speed;
                }
            }
            if (PressedW) {
                if (Tiles[(PlayerY-12-Speed)/64][(PlayerX+18)/64]!=2 || Tiles[(PlayerY-12-Speed)/64][(PlayerX-18)/64]!=2) {
                    PlayerY = (PlayerY-12-Speed)/64*64+13+64;
                } else {
                    PlayerY -= Speed;
                }
            }
        }
    }
}
