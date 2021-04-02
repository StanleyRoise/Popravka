public class DrunkenKnight extends Monsters{

    public DrunkenKnight(Board board, int color, int xLoc, int yLoc){
        super(board, color, xLoc, yLoc);
    }

    public boolean canMoveTo(int xPosition, int yPosition){
        if(canMoveGenerics(xPosition,yPosition)){
            return DrunkMovement(xPosition, yPosition);
        }
        return false;
    }


    private boolean DrunkMovement(int xPosition, int yPosition){
        int one_step;
        int two_step;
        Monsters target = MonsterBoard.pieceAt(xPosition, yPosition);

        if (this.getColor() == Player2){
            one_step = 1;
            two_step = 2;
        }
        else{
            one_step = -1;
            two_step = -2;
        }

        // Moving one step forward
        if (xPosition - this.getXLocation() == one_step){
            // Straight
            if (yPosition == this.getYLocation() && target == null){
                return true;
            }

        }
        // Two spaces
        else if (!hasMoved){
            if (xPosition - this.getXLocation() == two_step){
                if (yPosition == this.getYLocation() && target == null){
                    return true;
                }
            }
        }

        return false;
    }
}
