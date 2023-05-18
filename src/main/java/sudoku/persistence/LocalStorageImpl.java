package sudoku.persistence;

import javafx.scene.chart.PieChart;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;

import java.io.*;

public class LocalStorageImpl implements IStorage {
  private  static File GAME_DATA = new File(
      System.getProperty("user.home"),
      "gamedata.txt"
  );
  @Override
  public void updateGameData(SudokuGame game) throws IOException {
    try{
      FileOutputStream fileOutputSTream = new FileOutputStream(GAME_DATA);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputSTream);
      objectOutputStream.writeObject(game);
      objectOutputStream.close();
    } catch(IOException e) {
      throw new IOException("Unable to access Game Data");
    }

  }

  @Override
  public SudokuGame getGameData() throws IOException {
    FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
    try{
      SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
      objectInputStream.close();
      return gameState;
    } catch (ClassNotFoundException e) {
      throw new IOException("File Not FOund");
    }

  }
}
