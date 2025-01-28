package chess;

import java.util.Collection;
import java.util.*;
import java.util.Arrays;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor teamColor;
    private ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.teamColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return teamColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> PossibleMoves = new HashSet<>();
        switch(this.type){
            case KING -> PossibleMoves.addAll(KingMoves(board,myPosition));
            case QUEEN -> PossibleMoves.addAll(QueenMoves(board,myPosition));
            case BISHOP -> PossibleMoves.addAll(BishopMoves(board,myPosition));
            case KNIGHT -> PossibleMoves.addAll(KnightMoves(board,myPosition));
            case ROOK -> PossibleMoves.addAll(RookMoves(board,myPosition));
            case PAWN -> PossibleMoves.addAll(PawnMoves(board,myPosition));
        }
        return PossibleMoves;
    }
    public Set<ChessMove> KingMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> moves = new HashSet<>();
        if(myPosition.getRow() < 7){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()),null));
            }
        }
        if(myPosition.getColumn() < 7){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow(),myPosition.getColumn()+1));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow(), myPosition.getColumn()+1),null));
            }
        }
        if(myPosition.getRow() > 1){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()),null));
            }
        }
        if(myPosition.getColumn() > 1){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow(),myPosition.getColumn()-1));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow(), myPosition.getColumn()-1),null));
            }
        }
        if(myPosition.getRow() < 7 && myPosition.getColumn() < 7){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()+1));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1),null));
            }
        }
        if(myPosition.getRow() < 7 && myPosition.getColumn() > 1){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()-1));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()-1),null));
            }
        }
        if(myPosition.getRow() > 1 && myPosition.getColumn() < 7){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()+1));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1),null));
            }
        }
        if(myPosition.getRow() > 1 && myPosition.getColumn() > 1){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()-1));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1),null));
            }
        }
        return moves;
    }
    public Set<ChessMove> QueenMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> moves = new HashSet<>();
        moves.addAll(BishopMoves(board, myPosition));
        moves.addAll(RookMoves(board, myPosition));
        return moves;
    }
    public Set<ChessMove> RookMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> moves = new HashSet<>();
        for (int row = myPosition.getRow()+1; row <= 8; row++) {
            ChessPiece piece = board.getPiece(new ChessPosition(row,myPosition.getColumn()));
            if(piece != null){
            if(piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(row,myPosition.getColumn()),null));
            }break;}
            moves.add(new ChessMove(myPosition,new ChessPosition(row,myPosition.getColumn()),null));
        }
        for (int row = myPosition.getRow()-1; row > 0; row--) {
            ChessPiece piece = board.getPiece(new ChessPosition(row,myPosition.getColumn()));
            if(piece != null){
                if(piece.getTeamColor() != this.teamColor){
                    moves.add(new ChessMove(myPosition,new ChessPosition(row,myPosition.getColumn()),null));
                }break;}
            moves.add(new ChessMove(myPosition,new ChessPosition(row,myPosition.getColumn()),null));
        }
        for (int col = myPosition.getColumn()+1; col <= 8; col++) {
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow(),col));
            if(piece != null){
                if(this.teamColor != piece.getTeamColor()){
                    moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow(), col),null));
                }break;}
            moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow(), col),null));
        }
        for (int col = myPosition.getColumn()-1; col > 0; col--) {
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow(), col));
            if(piece != null){
                if(this.teamColor != piece.getTeamColor()){
                    moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow(), col),null));
                }break;}
            moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow(), col),null));
        }
        return moves;
    }
    public Set<ChessMove> BishopMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> moves = new HashSet<>();
        for(int r = myPosition.getRow()+1, c = myPosition.getColumn()+1; r <= 8 && c <= 8; r++,c++) {
            ChessPiece piece = board.getPiece(new ChessPosition(r,c));
            if(piece != null){
                if(teamColor != piece.getTeamColor()){
                    moves.add(new ChessMove(myPosition,new ChessPosition(r,c),null));
                }break;
            }
            moves.add(new ChessMove(myPosition,new ChessPosition(r,c),null));
        }
        for(int r = myPosition.getRow()+1, c = myPosition.getColumn()-1; r <= 8 && c >0 ; r++,c--) {
            ChessPiece piece = board.getPiece(new ChessPosition(r,c));
            if(piece != null){
                if(teamColor != piece.getTeamColor()){
                    moves.add(new ChessMove(myPosition,new ChessPosition(r,c),null));
                }break;
            }
            moves.add(new ChessMove(myPosition,new ChessPosition(r,c),null));
        }
        for(int r = myPosition.getRow()-1, c = myPosition.getColumn()+1; r >0 && c <= 8; r--,c++) {
            ChessPiece piece = board.getPiece(new ChessPosition(r,c));
            if(piece != null){
                if(teamColor != piece.getTeamColor()){
                    moves.add(new ChessMove(myPosition,new ChessPosition(r,c),null));
                }break;
            }
            moves.add(new ChessMove(myPosition,new ChessPosition(r,c),null));
        }
        for(int r = myPosition.getRow()-1, c = myPosition.getColumn()-1; r >0 && c >0; r--,c--) {
            ChessPiece piece = board.getPiece(new ChessPosition(r,c));
            if(piece != null){
                if(teamColor != piece.getTeamColor()){
                    moves.add(new ChessMove(myPosition,new ChessPosition(r,c),null));
                }break;
            }
            moves.add(new ChessMove(myPosition,new ChessPosition(r,c),null));
        }

        return moves;
    }
    public Set<ChessMove> KnightMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> moves = new HashSet<>();
        if(myPosition.getRow()+2 <=8 && myPosition.getColumn()+1 <=8){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()+2,myPosition.getColumn()+1));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+2, myPosition.getColumn()+1),null));
            }
        }
        if(myPosition.getRow()+1 <=8 && myPosition.getColumn()+2 <=8){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()+2));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()+2),null));
            }
        }
        if(myPosition.getRow()-2 > 0 && myPosition.getColumn()+1 <=8){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()-2,myPosition.getColumn()+1));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-2, myPosition.getColumn()+1),null));
            }
        }
        if(myPosition.getRow()-1 >0 && myPosition.getColumn()+2 <=8){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()+2));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+2),null));
            }
        }if(myPosition.getRow()-2 >0 && myPosition.getColumn()-1 >0){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()-2,myPosition.getColumn()-1));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-2, myPosition.getColumn()-1),null));
            }
        }
        if(myPosition.getRow()-1 >0 && myPosition.getColumn()-2 >0){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()-2));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-2),null));
            }
        }
        if(myPosition.getRow()+2 <=8 && myPosition.getColumn()-1 >0){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()+2,myPosition.getColumn()-1));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+2, myPosition.getColumn()-1),null));
            }
        }
        if(myPosition.getRow()+1 <=8 && myPosition.getColumn()-2 >0 ){
            ChessPiece piece = board.getPiece(new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()-2));
            if(piece == null || piece.getTeamColor() != this.teamColor){
                moves.add(new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()-2),null));
            }
        }
        return moves;
    }
    public Set<ChessMove> PawnMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> moves = new HashSet<>();

        int direction = (teamColor == ChessGame.TeamColor.WHITE)? 1 : -1;

        boolean isFirstMove = (teamColor == ChessGame.TeamColor.BLACK && myPosition.getRow() == 7 || teamColor == ChessGame.TeamColor.WHITE && myPosition.getRow() == 2);

        int row1 = myPosition.getRow() + direction;
        int col1 = myPosition.getColumn();
        if(row1 > 0 && row1 <= 8 && col1 > 0 && col1 <= 8){
            ChessPiece piece = board.getPiece(new ChessPosition(row1, col1));
            if(piece == null){
                if(teamColor == ChessGame.TeamColor.BLACK && row1 == 1 || teamColor == ChessGame.TeamColor.WHITE && row1 == 8){
                    moves.add(new ChessMove(myPosition,new ChessPosition(row1,col1),PieceType.QUEEN));
                    moves.add(new ChessMove(myPosition,new ChessPosition(row1,col1),PieceType.ROOK));
                    moves.add(new ChessMove(myPosition,new ChessPosition(row1,col1),PieceType.BISHOP));
                    moves.add(new ChessMove(myPosition,new ChessPosition(row1,col1),PieceType.KNIGHT));
                }else{
                    moves.add(new ChessMove(myPosition,new ChessPosition(row1,col1),null));
                }

                int row2 = myPosition.getRow() + 2 * direction;
                if(isFirstMove){
                    ChessPiece twoMove = board.getPiece(new ChessPosition(row2,col1));
                    if(twoMove == null) {
                        moves.add(new ChessMove(myPosition,new ChessPosition(row2,col1),null));
                    }
                }
            }
        }


        int row3 = myPosition.getRow() + direction;
        int col3 = myPosition.getColumn()+1 ;
        if(row3 > 0 && row3 <= 8 && col3 > 0 && col3 <= 8) {
            ChessPiece piece = board.getPiece(new ChessPosition(row3,col3));
            if(piece != null && piece.getTeamColor() != this.teamColor){
                if(teamColor == ChessGame.TeamColor.BLACK && myPosition.getRow() == 0 || teamColor == ChessGame.TeamColor.WHITE && myPosition.getRow()== 7){
                    moves.add(new ChessMove(myPosition,new ChessPosition(row3,col3),PieceType.QUEEN));
                    moves.add(new ChessMove(myPosition,new ChessPosition(row3,col3),PieceType.ROOK));
                    moves.add(new ChessMove(myPosition,new ChessPosition(row3,col3),PieceType.BISHOP));
                    moves.add(new ChessMove(myPosition,new ChessPosition(row3,col3),PieceType.KNIGHT));
                }else
                    moves.add(new ChessMove(myPosition,new ChessPosition(row3,col3),null));
        }
        }

        int row4 = myPosition.getRow() + direction;
        int col4 = myPosition.getColumn() - 1;
        if(row4 > 0 && row4 <= 8 && col4 > 0 && col4 <= 8) {
            ChessPiece piece = board.getPiece(new ChessPosition(row4,col4));
            if(piece != null && this.teamColor!= piece.getTeamColor()){
                if(this.teamColor == ChessGame.TeamColor.BLACK && myPosition.getRow() == 2 || this.teamColor == ChessGame.TeamColor.WHITE && myPosition.getRow() == 7){
                    moves.add(new ChessMove(myPosition,new ChessPosition(row4,col4),PieceType.QUEEN));
                    moves.add(new ChessMove(myPosition,new ChessPosition(row4,col4),PieceType.ROOK));
                    moves.add(new ChessMove(myPosition,new ChessPosition(row4,col4),PieceType.BISHOP));
                    moves.add(new ChessMove(myPosition,new ChessPosition(row4,col4),PieceType.KNIGHT));
                }else
                    moves.add(new ChessMove(myPosition,new ChessPosition(row4,col4),null));
              }
        }
        return moves;
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "teamColor=" + teamColor +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return teamColor == that.teamColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor, type);
    }
}
