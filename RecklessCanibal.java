public class RecklessCanibal extends Monsters{
    public RecklessCanibal(Board board, int color, int xLoc, int yLoc){
        super(board, color, xLoc, yLoc);
    }

    public boolean canMoveTo(int xPosition, int yPosition){
        if(canMoveGenerics(xPosition,yPosition)){
            return rookMovement(xPosition, yPosition);
        }
        return false;
    }


    private boolean rookMovement(int xPosition, int yPosition){
        return isMovingStraight(xPosition, yPosition);
    }
}
