public class MagicCat extends Monsters{
    public MagicCat(Board board, int color, int xLoc, int yLoc){
        super(board, color, xLoc, yLoc);
    }

    public boolean canMoveTo(int xPosition, int yPosition){
        if(canMoveGenerics(xPosition,yPosition)){
            return MagicCatMovement(xPosition, yPosition);
        }
        return false;
    }


    private boolean MagicCatMovement(int xPosition, int yPosition){
        if (isMovingStraight(xPosition, yPosition) ||
                isMovingDiagonal(xPosition, yPosition))
            return true;
        return false;
    }
}
