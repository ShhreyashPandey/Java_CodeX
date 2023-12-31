import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Game2048 {
    private static final int SIZE = 4;
    private int[][] grid;
    private Random random;
    private int score;
    private int highScore;
    private JFrame frame;
    private JPanel gridPanel;
    private JLabel[][] gridLabels;
    private JLabel scoreLabel;
    private JLabel highScoreLabel;
    private boolean winConditionReached;

    public Game2048() {
        grid = new int[SIZE][SIZE];
        random = new Random();
        score = 0;
        highScore = 0;
        frame = new JFrame("2048 Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLayout(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
        gridLabels = new JLabel[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gridLabels[i][j] = new JLabel("", JLabel.CENTER);
                gridLabels[i][j].setFont(new Font("Courier", Font.BOLD, 24));
                gridLabels[i][j].setOpaque(true);
                gridLabels[i][j].setBackground(Color.LIGHT_GRAY);
                gridPanel.add(gridLabels[i][j]);
            }
        }

        frame.add(gridPanel, BorderLayout.CENTER);
        JPanel infoPanel = new JPanel(new GridLayout(2, 2));
        scoreLabel = new JLabel("Score: 0", JLabel.CENTER);
        highScoreLabel = new JLabel("High Score: 0", JLabel.CENTER);
        infoPanel.add(scoreLabel);
        infoPanel.add(highScoreLabel);
        frame.add(infoPanel, BorderLayout.NORTH);

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    moveUp();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    moveDown();
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    moveLeft();
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    moveRight();
                }
                updateGridLabels();
                updateScore();
                if (isGameOver()) {
                    showGameOverMessage();
                }
            }
        });

        frame.setFocusable(true);
        frame.requestFocus();
        frame.setVisible(true);
        initializeGrid();
        updateGridLabels();
    }

    public void initializeGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = 0;
            }
        }
        addNewNumber();
        addNewNumber();
    }

    public void addNewNumber() {
        int row, col;
        do {
            row = random.nextInt(SIZE);
            col = random.nextInt(SIZE);
        } while (grid[row][col] != 0);

        grid[row][col] = (random.nextInt(2) + 1) * 2;
    }

    public void updateGridLabels() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gridLabels[i][j].setText(grid[i][j] == 0 ? "" : String.valueOf(grid[i][j]));
                gridLabels[i][j].setBackground(getTileColor(grid[i][j]));
            }
        }
    }

    public Color getTileColor(int value) {
        switch (value) {
            case 2:
                return new Color(238, 228, 218);
            case 4:
                return new Color(237, 224, 200);
            case 8:
                return new Color(242, 177, 121);
            case 16:
                return new Color(245, 149, 99);
            case 32:
                return new Color(246, 124, 95);
            case 64:
                return new Color(246, 94, 59);
            case 128:
                return new Color(237, 207, 114);
            case 256:
                return new Color(237, 204, 97);
            case 512:
                return new Color(237, 200, 80);
            case 1024:
                return new Color(237, 197, 63);
            case 2048:
                return new Color(237, 194, 46);
            default:
                return Color.WHITE;
        }
    }

    public void moveUp() {
        boolean moved = false;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 1; i < SIZE; i++) {
                if (grid[i][j] != 0) {
                    for (int row = i - 1; row >= 0; row--) {
                        if (grid[row][j] == 0) {
                            grid[row][j] = grid[row + 1][j];
                            grid[row + 1][j] = 0;
                            moved = true;
                        } else if (grid[row][j] == grid[row + 1][j]) {
                            grid[row][j] *= 2;
                            score += grid[row][j];
                            grid[row + 1][j] = 0;
                            moved = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if (moved) {
            addNewNumber();
        }
    }

    public void moveDown() {
        boolean moved = false;
        for (int j = 0; j < SIZE; j++) {
            for (int i = SIZE - 2; i >= 0; i--) {
                if (grid[i][j] != 0) {
                    for (int row = i + 1; row < SIZE; row++) {
                        if (grid[row][j] == 0) {
                            grid[row][j] = grid[row - 1][j];
                            grid[row - 1][j] = 0;
                            moved = true;
                        } else if (grid[row][j] == grid[row - 1][j]) {
                            grid[row][j] *= 2;
                            score += grid[row][j];
                            grid[row - 1][j] = 0;
                            moved = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if (moved) {
            addNewNumber();
        }
    }

    public void moveLeft() {
        boolean moved = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 1; j < SIZE; j++) {
                if (grid[i][j] != 0) {
                    for (int col = j - 1; col >= 0; col--) {
                        if (grid[i][col] == 0) {
                            grid[i][col] = grid[i][col + 1];
                            grid[i][col + 1] = 0;
                            moved = true;
                        } else if (grid[i][col] == grid[i][col + 1]) {
                            grid[i][col] *= 2;
                            score += grid[i][col];
                            grid[i][col + 1] = 0;
                            moved = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if (moved) {
            addNewNumber();
        }
    }

    public void moveRight() {
        boolean moved = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = SIZE - 2; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    for (int col = j + 1; col < SIZE; col++) {
                        if (grid[i][col] == 0) {
                            grid[i][col] = grid[i][col - 1];
                            grid[i][col - 1] = 0;
                            moved = true;
                        } else if (grid[i][col] == grid[i][col - 1]) {
                            grid[i][col] *= 2;
                            score += grid[i][col];
                            grid[i][col - 1] = 0;
                            moved = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if (moved) {
            addNewNumber();
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 2048) {
                    winConditionReached = true;
                    return true;
                }
                if (grid[i][j] == 0 ||
                        (i > 0 && grid[i][j] == grid[i - 1][j]) ||
                        (i < SIZE - 1 && grid[i][j] == grid[i + 1][j]) ||
                        (j > 0 && grid[i][j] == grid[i][j - 1]) ||
                        (j < SIZE - 1 && grid[i][j] == grid[i][j + 1])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showGameOverMessage() {
        String message;
        if (winConditionReached) {
            message = "Game Complete!\nContinue playing?";
        } else {
            message = "Game over! Restart?";
        }
        int choice = JOptionPane.showConfirmDialog(frame, message, "Game Over", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    public void restartGame() {
        score = 0;
        winConditionReached = false;
        updateScore();
        initializeGrid();
        updateGridLabels();
    }

    public void updateScore() {
        scoreLabel.setText("Score: " + score);
        if (score > highScore) {
            highScore = score;
            highScoreLabel.setText("High Score: " + highScore);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
