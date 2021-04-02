public class Monsters {
    public static final int Player1 = 0;
    public static final int Player2 = 1;
    private int xLocation;
    private int yLocation;
    private int color;
    protected boolean hasMoved;
    protected Board MonsterBoard;

    public Monsters(Board board, int color){
        this.MonsterBoard = board;
        this.color = color;
        hasMoved = false;
        xLocation = -1;
        yLocation = -1;
    }

    public Monsters(Board board, int color, int xLoc, int yLoc){
        this.MonsterBoard = board;
        this.color = color;
        this.hasMoved = false;
        this.xLocation = xLoc;
        this.yLocation = yLoc;

        MonsterBoard.placePiece(this, xLoc, yLoc);
    }


    public boolean canMoveTo(int xPosition, int yPosition){
        return canMoveGenerics(xPosition, yPosition);
    }


    protected boolean canMoveGenerics(int xPosition, int yPosition){
        if (MonsterBoard.isInBounds(xPosition, yPosition)){
            Monsters location = MonsterBoard.pieceAt(xPosition, yPosition);

            if (location == null) return true;
            if (location.getColor() != this.color) return true;
        }
        return false;
    }


    public void moveTo(int xPosition, int yPosition){
        if (MonsterBoard.pieceAt(xLocation, yLocation) == this)
            MonsterBoard.removeFromBoard(this);
        this.xLocation = xPosition;
        this.yLocation = yPosition;

        Monsters target = MonsterBoard.pieceAt(xPosition, yPosition);
        if (target != null){
            this.capturePiece(target);
        }


        MonsterBoard.placePiece(this, xPosition, yPosition);
        hasMoved = true;
    }

    /**
     * Removes the current monster off of the board
     */
    public void removePiece() {
        MonsterBoard.removeFromBoard(this);
        xLocation = -1;
        yLocation = -1;
    }


    public void capturePiece(Monsters capturedPiece){
        capturedPiece.removePiece();
    }


    public boolean onBoard(){
        if (MonsterBoard.isInBounds(xLocation, yLocation))
            return true;
        return false;
    }

    // Getters/Setters Below

    public int getXLocation(){
        return xLocation;
    }

    public int getYLocation(){
        return yLocation;
    }

    public int getColor(){
        return color;
    }

    public Board getBoard(){
        return MonsterBoard;
    }


    protected boolean isMovingStraight(int xPosition, int yPosition) {
        int currX = this.getXLocation();
        int currY = this.getYLocation();

        int smallerVal;
        int largerVal;

        // Fixed X Position
        if (currX == xPosition){
            if (currY > yPosition){
                smallerVal = yPosition;
                largerVal = currY;
            }
            else if (yPosition > currY){
                smallerVal = currY;
                largerVal = yPosition;
            }
            else return false;

            // Loop to determine if any piece is between
            // target location and this piece.
            smallerVal++;
            for(; smallerVal < largerVal; smallerVal++){
                if (MonsterBoard.pieceAt(currX, smallerVal) != null){
                    return false;
                }
            }
            return true;
        }

        // Fixed Y Position
        if (currY == yPosition){
            if (currX > xPosition){
                smallerVal = xPosition;
                largerVal = currX;
            }
            else if (xPosition > currX){
                smallerVal = currX;
                largerVal = xPosition;
            }
            else return false;

            // Loop to determine if any piece is between
            // target location and this piece.
            smallerVal++;
            for(; smallerVal < largerVal; smallerVal++){
                if (MonsterBoard.pieceAt(smallerVal, currY) != null){
                    return false;
                }
            }
            return true;
        }

        return false;
    }


    protected boolean isMovingDiagonal(int xPosition, int yPosition) {
        int xStart = 0;
        int yStart = 0;
        int xFinish = 1;
        //int yFinish = 1;

        //Check if movement is diagonal
        int xTotal = Math.abs(xPosition - this.getXLocation());
        int yTotal = Math.abs(yPosition - this.getYLocation());

        if (xTotal == yTotal){
            if (xPosition < this.getXLocation()){
                xStart = xPosition;
                xFinish = this.getXLocation();
            }
            else if (xPosition > this.getXLocation()){
                xStart = this.getXLocation();
                xFinish = xPosition;
            }
            else
                return false;

            if (yPosition < this.getYLocation()){
                yStart = yPosition;
                //yFinish = this.getYLocation();
            }
            else if (yPosition > this.getYLocation()){
                yStart = this.getYLocation();
                //yFinish = yPosition;
            }
            else
                return false;

            xStart++;
            yStart++;

            // Loop to see if any piece is in between
            for(;xStart < xFinish; xStart++, yStart++){
                if (MonsterBoard.pieceAt(xStart, yStart) != null){
                    return false;
                }
            }

            return true;
        }

        return false;
    }
