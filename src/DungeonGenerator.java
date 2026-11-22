import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

class DungeonGenerator {

    public int[][] CreateDungeon(int SizeX, int SizeY) {
        Random random = new Random();
        int[][] arr = new int[SizeY][SizeX];
        for (int i = 0; i < SizeY; i++) {
            for (int j = 0; j < SizeX; j++) {
                arr[i][j] = -1;
            }
        }
        List<int[]> ReachableRooms = new ArrayList<>();
        List<int[]> queue = new ArrayList<>();
        int i = random.nextInt(15)+15;
        arr[SizeY/2][SizeX/2] = i;
        if (RoomExits[i][0]==1) {
            queue.add(new int[]{SizeY/2-1,SizeX/2});
            ReachableRooms.add(new int[]{SizeY/2-1,SizeX/2});
        }
        if (RoomExits[i][1]==1) {
            queue.add(new int[]{SizeY/2,SizeX/2+1});
            ReachableRooms.add(new int[]{SizeY/2,SizeX/2+1});
        }
        if (RoomExits[i][2]==1) {
            queue.add(new int[]{SizeY/2+1,SizeX/2});
            ReachableRooms.add(new int[]{SizeY/2+1,SizeX/2});
        }
        if (RoomExits[i][3]==1) {
            queue.add(new int[]{SizeY/2,SizeX/2-1});
            ReachableRooms.add(new int[]{SizeY/2,SizeX/2-1});
        }
        while (!(queue.isEmpty())) {
            int x = queue.getFirst()[1];
            int y = queue.getFirst()[0];
            int UpExit;
            int DownExit;
            int LeftExit;
            int RightExit;
            if  (x==0) {
                LeftExit = 0;
            } else if (arr[y][x-1] == -1) {
                LeftExit = -1;
            } else {
                LeftExit = RoomExits[arr[y][x-1]][1];
            }
            if  (x==SizeX-1) {
                RightExit = 0;
            } else if (arr[y][x+1] == -1) {
                RightExit = -1;
            } else {
                RightExit = RoomExits[arr[y][x+1]][3];
            }
            if  (y==SizeY-1) {
                DownExit = 0;
            } else if (arr[y+1][x] == -1) {
                DownExit = -1;
            } else {
                DownExit = RoomExits[arr[y+1][x]][0];
            }
            if  (y==0) {
                UpExit = 0;
            } else if (arr[y-1][x] == -1) {
                UpExit = -1;
            } else {
                UpExit = RoomExits[arr[y-1][x]][2];
            }
            queue.removeFirst();
            int m = random.nextInt(45)+15;
            while (!((RoomExits[m][0]==UpExit || UpExit == -1) && (RoomExits[m][1]==RightExit || RightExit == -1) && (RoomExits[m][2]==DownExit || DownExit == -1) && (RoomExits[m][3]==LeftExit || LeftExit == -1))) {
                m = random.nextInt(45)+15;
            }
            arr[y][x] = m;
            if (RoomExits[m][0]==1 && arr[y-1][x]==-1 && !(IsInList(queue,new int[]{y-1,x}))) {
                queue.add(new int[]{y-1,x});
                if (!(IsInList(ReachableRooms,new int[]{y-1,x}))) {
                    ReachableRooms.add(new int[]{y-1,x});
                }
            }
            if (RoomExits[m][1]==1 && arr[y][x+1]==-1 && !(IsInList(queue,new int[]{y,x+1}))) {
                queue.add(new int[]{y,x+1});
                if (!(IsInList(ReachableRooms,new int[]{y,x+1}))) {
                    ReachableRooms.add(new int[]{y,x+1});
                }
            }
            if (RoomExits[m][2]==1 && arr[y+1][x]==-1 && !(IsInList(queue,new int[]{y+1,x}))) {
                queue.add(new int[]{y+1,x});
                if (!(IsInList(ReachableRooms,new int[]{y+1,x}))) {
                    ReachableRooms.add(new int[]{y+1,x});
                }
            }
            if (RoomExits[m][3]==1 && arr[y][x-1]==-1 && !(IsInList(queue,new int[]{y,x-1}))) {
                queue.add(new int[]{y,x-1});
                if (!(IsInList(ReachableRooms,new int[]{y,x-1}))) {
                    ReachableRooms.add(new int[]{y,x-1});
                }
            }
        }
        i = random.nextInt(ReachableRooms.size());
        int[] ExitRoom = ReachableRooms.get(i);
        int k = 0;
        while (!(RoomExits[k][0] == RoomExits[arr[ExitRoom[0]][ExitRoom[1]]][0] && RoomExits[k][1] == RoomExits[arr[ExitRoom[0]][ExitRoom[1]]][1] && RoomExits[k][2] == RoomExits[arr[ExitRoom[0]][ExitRoom[1]]][2] && RoomExits[k][3] == RoomExits[arr[ExitRoom[0]][ExitRoom[1]]][3])) {
            k++;
            if (k>14) System.out.println(ExitRoom[1]+" "+ExitRoom[0]+ " "+k+" "+arr[ExitRoom[0]][ExitRoom[1]]);
        }
        arr[ExitRoom[0]][ExitRoom[1]] = k;
        return arr;
    }


    public boolean IsInList(List<int[]> queue, int[] pair) {
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i)[0] == pair[0] && queue.get(i)[1] == pair[1]) {
                return true;
            }
        }
        return false;
    }
    byte[][] RoomExits = {
            {1,1,1,1},
            {0,1,1,1},
            {1,0,1,1},
            {1,1,0,1},
            {1,1,1,0},
            {1,1,0,0},
            {1,0,1,0},
            {1,0,0,1},
            {0,1,1,0},
            {0,0,1,1},
            {0,1,0,1},
            {1,0,0,0},
            {0,1,0,0},
            {0,0,1,0},
            {0,0,0,1},
            {1,1,1,1},
            {0,1,1,1},
            {1,0,1,1},
            {1,1,0,1},
            {1,1,1,0},
            {1,1,0,0},
            {1,0,1,0},
            {1,0,0,1},
            {0,1,1,0},
            {0,0,1,1},
            {0,1,0,1},
            {1,0,0,0},
            {0,1,0,0},
            {0,0,1,0},
            {0,0,0,1},
            {1,1,1,1},
            {0,1,1,1},
            {1,0,1,1},
            {1,1,0,1},
            {1,1,1,0},
            {1,1,0,0},
            {1,0,1,0},
            {1,0,0,1},
            {0,1,1,0},
            {0,0,1,1},
            {0,1,0,1},
            {1,0,0,0},
            {0,1,0,0},
            {0,0,1,0},
            {0,0,0,1},
            {1,1,1,1},
            {0,1,1,1},
            {1,0,1,1},
            {1,1,0,1},
            {1,1,1,0},
            {0,0,1,1},
            {0,1,0,1},
            {0,1,1,0},
            {1,0,0,1},
            {1,0,1,0},
            {1,1,0,0},
            {1,0,0,0},
            {0,1,0,0},
            {0,0,1,0},
            {0,0,0,1}
    };



}
