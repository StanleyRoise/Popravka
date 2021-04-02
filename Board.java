public class Board {
    private Monsters[][] MonsterBoard;

    public Board(int xDimension, int yDimension){
        MonsterBoard = new Monsters[xDimension][yDimension];
    }


    public boolean isEmptyPosition(int xPosition, int yPosition){
        if (isInBounds(xPosition, yPosition)){
            if (MonsterBoard[xPosition][yPosition] == null)
                return true;
        }
        return false;
    }


    public boolean isInBounds(int xPosition, int yPosition){
        if (xPosition < getXDimension() && xPosition >= 0 &&
                yPosition < getYDimension() && yPosition >= 0)
            return true;
        return false;
    }


    public Monsters pieceAt(int xPosition, int yPosition){
        if (isInBounds(xPosition, yPosition)){
            return MonsterBoard[xPosition][yPosition];
        }
        return null;
    }


    public void displayBoard(){
        for (int xBoard = 0; xBoard < getXDimension(); xBoard++){
            for (int yBoard = 0; yBoard < getYDimension(); yBoard++){
                if (MonsterBoard[xBoard][yBoard] == null)
                    System.out.print(".");
                else{
                    if (MonsterBoard[xBoard][yBoard] instanceof DrunkenKnight)
                        System.out.print("DK");

                    else if (MonsterBoard[xBoard][yBoard] instanceof MagicCat)
                        System.out.print("MC");
                    else if (MonsterBoard[xBoard][yBoard] instanceof SufSam)
                        System.out.print("SS");
                    else if (MonsterBoard[xBoard][yBoard] instanceof RecklessCanibal)
                        System.out.print("RK");
                    else if (MonsterBoard[xBoard][yBoard] instanceof DogEatingBug)
                        System.out.print("DB");
                    else
                        System.out.print("x");
                }
            }
            System.out.println();
        }
    }

    // Getters/Setters below

    public int getXDimension(){
        return MonsterBoard[0].length;
    }

    public int getYDimension(){
        return MonsterBoard.length;
    }

    public Monsters[][] getChessBoard(){
        return MonsterBoard;
    }

    public void removeFromBoard(Monsters removePiece){
        int oldXLocation = removePiece.getXLocation();
        int oldYLocation = removePiece.getYLocation();

        MonsterBoard[oldXLocation][oldYLocation] = null;
    }

    public void placePiece(Monsters chessPiece, int xPosition, int yPosition){
        if (isInBounds(xPosition, yPosition))
            MonsterBoard[xPosition][yPosition] = chessPiece;
    }
}
