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
    boolean PressedC;
    boolean PressedE;
    boolean PressedCPreviousTick;
    int RoomX;
    int RoomY;
    int[][] Dungeon = new int[5][5];
    boolean[][] DungeonInteraction = new boolean[5][5];
    double PlayerX = 980;
    double PlayerY = 540;
    double PlayerSpeedX = 0;
    double PlayerSpeedY = 0;
    double Inertion = 0.5;
    double DashInertion = 0.65;
    double Speed = 10;  // (MaxSpeed+Speed)*Inertion=MaxSpeed => MaxSpeed = Speed*Inertion/(1-Inertion), Speed = MaxSpeed*(1-Inertion)/Inertion
    int[][] Tiles = new int[29][17];

    RoomReader Rooms = new RoomReader();

    public int[][] getTiles() {
        return(Tiles);
    }
    public void SetTiles() {
        try {
            System.arraycopy(Rooms.ReadRoom(Dungeon[RoomY][RoomX]), 0, Tiles, 0, 17);
            if (DungeonInteraction[RoomY][RoomX] && Dungeon[RoomY][RoomX] < 45 && Dungeon[RoomY][RoomX] > 29) {
                Tiles[8][14] = 13;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void GenerateDungeon(int SizeX, int SizeY) {
        DungeonGenerator DG = new DungeonGenerator();
        DungeonInteraction = new boolean[SizeY][SizeX];
        Dungeon = DG.CreateDungeon(SizeX,SizeY);
        RoomX = SizeX/2;
        RoomY = SizeY/2;
        PlayerX = 980;
        PlayerY = 540;
        for (int i = 0; i < SizeY; i++) {
            for (int j = 0; j < SizeX; j++) {
                System.out.print(Dungeon[i][j]+" ");
            }
            System.out.println();
        }
    }


    public void tick() {
        if (StageID==-1) {
            if (PlayerX+PlayerSpeedX > 1824) {
                PlayerX = 32;
                RoomX++;
                System.out.println(RoomX+" "+RoomY);
                SetTiles();
            }
            if (PlayerX+PlayerSpeedX < 32) {
                PlayerX = 1824;
                RoomX--;
                System.out.println(RoomX+" "+RoomY);
                SetTiles();
            }
            if (PlayerY+PlayerSpeedY > 1056) {
                PlayerY = 32;
                RoomY ++;
                System.out.println(RoomX+" "+RoomY);
                SetTiles();
            }
            if (PlayerY+PlayerSpeedY < 32) {
                PlayerY = 1056;
                RoomY --;
                System.out.println(RoomX+" "+RoomY);
                SetTiles();
            }


            if (PressedA) {
                PlayerSpeedX-=Speed;
                if (PressedC && !(PressedCPreviousTick)) {
                    PlayerSpeedX = -90;
                }
            }
            if (PressedD) {
                PlayerSpeedX += Speed;
                if (PressedC && !(PressedCPreviousTick)) {
                    PlayerSpeedX = 90;
                }
            }
            if (PressedS) {
                PlayerSpeedY += Speed;
                if (PressedC && !(PressedCPreviousTick)) {
                    PlayerSpeedY = 90;
                }
            }
            if (PressedW) {
                PlayerSpeedY -= Speed;
                if (PressedC && !(PressedCPreviousTick)) {
                    PlayerSpeedY = -90;
                }
            }
            if (Math.abs(PlayerSpeedX) < 1) {
                PlayerSpeedX = 0;
            } else {
                if (Math.abs(PlayerSpeedX) > 60) {
                    PlayerSpeedX *= DashInertion;
                } else {
                    PlayerSpeedX *=Inertion;
                }
            }
            if (Math.abs(PlayerSpeedY) < 1) {
                PlayerSpeedY = 0;
            } else {
                if (Math.abs(PlayerSpeedY) > 60) {
                    PlayerSpeedY *= DashInertion;
                } else {
                    PlayerSpeedY *=Inertion;
                }
            }
            PressedCPreviousTick = PressedC;
            if (PressedE) {
                int PlayerTileY = Math.min(Math.max((int)Math.round(PlayerY)/64,0),16);
                int PlayerTileX = Math.min(Math.max((int)Math.round(PlayerX)/64,0),28);
                if (Tiles[PlayerTileY][PlayerTileX-1] == 12 || Tiles[PlayerTileY][PlayerTileX+1] == 12 || Tiles[PlayerTileY-1][PlayerTileX] == 12 || Tiles[PlayerTileY+1][PlayerTileX] == 12) {
                    Tiles[8][14] = 13;
                    DungeonInteraction[RoomY][RoomX] = true;
                }
                if (Tiles[PlayerTileY][PlayerTileX-1] == 11 || Tiles[PlayerTileY][PlayerTileX+1] == 11 || Tiles[PlayerTileY-1][PlayerTileX] == 11 || Tiles[PlayerTileY+1][PlayerTileX] == 11) {
                    GenerateDungeon(5,5);
                    SetTiles();
                }
            }
            if (Tiles[Math.min(Math.max((int)Math.round(PlayerY+12)/64,0),16)][Math.min(Math.max((int)Math.round(PlayerX+PlayerSpeedX+18)/64,0),28)]!=2 || Tiles[Math.min(Math.max((int)Math.round(PlayerY-12)/64,0),16)][Math.min(Math.max((int)Math.round(PlayerX+PlayerSpeedX+18)/64,0),28)]!=2 || Tiles[Math.min(Math.max((int)Math.round(PlayerY+12)/64,0),16)][Math.min(Math.max((int)Math.round(PlayerX+PlayerSpeedX-18)/64,0),28)]!=2 || Tiles[Math.min(Math.max((int)Math.round(PlayerY-12)/64,0),16)][Math.min(Math.max((int)Math.round(PlayerX+PlayerSpeedX-18)/64,0),28)]!=2) {
                PlayerSpeedX=0;
            }
            if (Tiles[Math.min(Math.max((int)Math.round(PlayerY+12+PlayerSpeedY)/64,0),16)][Math.min(Math.max((int)Math.round(PlayerX+18)/64,0),28)]!=2 || Tiles[Math.min(Math.max((int)Math.round(PlayerY-12+PlayerSpeedY)/64,0),16)][Math.min(Math.max((int)Math.round(PlayerX+18)/64,0),28)]!=2 || Tiles[Math.min(Math.max((int)Math.round(PlayerY+12+PlayerSpeedY)/64,0),16)][Math.min(Math.max((int)Math.round(PlayerX-18)/64,0),28)]!=2 || Tiles[Math.min(Math.max((int)Math.round(PlayerY-12+PlayerSpeedY)/64,0),16)][Math.min(Math.max((int)Math.round(PlayerX-18)/64,0),28)]!=2) {
                PlayerSpeedY=0;
            }
            PlayerX+=PlayerSpeedX;
            PlayerY+=PlayerSpeedY;
        }
    }
}
