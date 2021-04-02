import java.util.Scanner;
import java.util.LinkedList;

public class Game {
    public static final int Player1 = 0;
    public static final int Player2 = 1;

    Scanner userInput = new Scanner(System.in);

    private int currentPlayer;
    private Board MonsterBoard;
    private LinkedList<Monsters> blackPieces;
    private LinkedList<Monsters> whitePieces;
    private SufSam Player1SufSam;
    private SufSam Player2SufSam;
    private Object MagicCat;
    private Object DogEatingBug;

    public Game() {
        MonsterBoard = new Board(6, 6);
        currentPlayer = Player2;
        blackPieces = new LinkedList<Monsters>();
        whitePieces = new LinkedList<Monsters>();

        Player1SufSam = new SufSam(MonsterBoard, Player1, Player2, Player1);
        Player2SufSam = new SufSam(MonsterBoard, Player2, Player2, Player1);
        blackPieces.add(Player1SufSam);
        whitePieces.add(Player2SufSam);
    }


    public void testSetup() {
        Player1SufSam.moveTo(5, 0);
        Player2SufSam.moveTo(5, 1);
        this.addDrunkenKnight(Player1, 1, 1);
        this.MagicCat(Player1, 4, 0);
        this.RecklessCanibal(Player1, 3, 0);
        this.DogEatingBug(Player1, 2, 0);
        currentPlayer = Player1;
    }

    private void MagicCat(int player1, int i, int i1) {
    }


    public void gameLoop() {
        boolean continueGame = true;

        testSetup();

        while (continueGame) {
            MonsterBoard.displayBoard();

            if (isGameOver()) {
                break;
            }

            System.out.print("Which piece to move? X-loc: ");
            int nextX = userInput.nextInt();
            System.out.print("Y-loc: ");
            int nextY = userInput.nextInt();

            Monsters target = MonsterBoard.pieceAt(nextX, nextY);
            if (target == null) {
                System.out.println("That location is invalid");
                continueGame = false;
            } else if (target.getColor() != currentPlayer) {
                System.out.println("That is not your piece");
                continueGame = false;
            } else {
                System.out.print("Where to move this piece? x-loc: ");
                nextX = userInput.nextInt();
                System.out.print("Y-loc: ");
                nextY = userInput.nextInt();

                if (target.canMoveTo(nextX, nextY)) {
                    target.moveTo(nextX, nextY);
                } else {
                    System.out.println("Cannot move there");
                }
            }
        }
    }

    private boolean isGameOver() {
        return false;
    }




    public boolean canMove(int player){
        int oldX, oldY;
        Monsters target;
        LinkedList<Monsters> checkPieces;

        if (player == Player1)
            checkPieces = blackPieces;
        else
            checkPieces = whitePieces;

        for (int x = 0; x < MonsterBoard.getXDimension(); x++){
            for (int y = 0; y < MonsterBoard.getYDimension(); y++){
                // If any piece can move to this spot, move here
                // If king is still in check, then go to next location.
                for (Monsters currentPiece : checkPieces){
                    if (currentPiece.canMoveTo(x, y)){
                        //System.out.println(x + ", " + y);
                        target = MonsterBoard.pieceAt(x, y);
                        oldX = currentPiece.getXLocation();
                        oldY = currentPiece.getYLocation();

                        currentPiece.moveTo(x, y);


                    }
                }
            }
        }
        return false;
    }


    public void removePiece(Monsters removeThisPiece){
        removeThisPiece.removePiece();
        int color = removeThisPiece.getColor();

        if (color == Player1)
            blackPieces.remove(removeThisPiece);
        else
            whitePieces.remove(removeThisPiece);
    }

    public void switchPlayerTurn(){
        if (currentPlayer == Player2)
            currentPlayer = Player1;
        else currentPlayer = Player2;
    }

    public MagicCat addMagicCat(int color, int xloc, int yloc){
        MagicCat queen = new MagicCat(MonsterBoard, color, xloc, yloc);
        pieceToColorHelper(queen, color);

        return (MagicCat) MagicCat;
    }



    public RecklessCanibal RecklessCanibal(int color, int xloc, int yloc){
        RecklessCanibal recklessCanibal = new RecklessCanibal(MonsterBoard, color, xloc, yloc);
        pieceToColorHelper(recklessCanibal, color);

        return recklessCanibal;
    }

    public DogEatingBug DogEatingBug(int color, int xloc, int yloc){
        DogEatingBug bishop = new DogEatingBug(MonsterBoard, color, xloc, yloc);
        pieceToColorHelper(bishop, color);

        return (DogEatingBug) DogEatingBug;
    }

    public DrunkenKnight addDrunkenKnight(int color, int xloc, int yloc){
        DrunkenKnight pawn = new DrunkenKnight(MonsterBoard, color, xloc, yloc);
        pieceToColorHelper(pawn, color);

        return pawn;
    }

    private void pieceToColorHelper(Monsters piece, int color){
        if (color == Player1)
            blackPieces.add(piece);
        else
            whitePieces.add(piece);
    }

    public int getPlayerTurn(){
        return currentPlayer;
    }

    public void setPlayer(int player){
        currentPlayer = player;
    }

    public SufSam getBlackKing(){
        return Player1SufSam;
    }

    public SufSam getWhiteKing(){
        return Player2SufSam;
    }
