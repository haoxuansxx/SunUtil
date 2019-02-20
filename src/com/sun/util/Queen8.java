package com.sun.util;


/**
 * 国际象棋8皇后问题
 *
 * @Authon Sun
 */
public class Queen8 {

    /**
     * 棋盘格子的范围
     */
    private static final int MAX_NUM = 8;
    /**
     * 皇后的数量
     */
    private static final int MAX_QUEEN_NUM = 8;
    /**
     * 二维数组作为棋盘
     */
    public static int chessBoard[][] = new int[Queen8.MAX_NUM][Queen8.MAX_NUM];

    /**
     * Main 启动方法
     */
    public static void main(String[] args) {
        // 创建对象并启用回溯方法
        Queen8 queen8 = new Queen8();
        queen8.settleQueen(0);
        queen8.printChessBoard();
    }

    /**
     * 检查落点是否符合规则
     *
     * @param x 横轴落点
     * @param y 竖轴落点
     * @return True:符合、False：不符合
     */
    private boolean check(int x, int y) {
        for (int i = 0; i < y; i++) {
            // 检查纵向
            if (Queen8.chessBoard[x][i] == 1) {
                return false;
            }
            // 检查左倾斜向
            if (x - 1 - i >= 0 && Queen8.chessBoard[x - 1 - i][y - 1 - i] == 1) {
                return false;
            }
            // 检查右倾斜向
            if (x + 1 + i < Queen8.MAX_NUM && Queen8.chessBoard[x + 1 + i][y - 1 - i] == 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 递归回溯寻找结果
     *
     * @param y 当前递归行
     * @return True：找到结果、False：未找到结果
     */
    private boolean settleQueen(int y) {
        // 行数超过皇后数，说明已经找出答案
        if (y == Queen8.MAX_QUEEN_NUM) {
            return true;
        }
        // 遍历当前行，逐一格子验证
        for (int i = 0; i < Queen8.MAX_NUM; i++) {
            // 为当前行清零，以免在回溯的时候出现脏数据
            for (int x = 0; x < Queen8.MAX_NUM; x++) {
                Queen8.chessBoard[x][y] = 0;
            }
            // 检查是否符合规则，如果符合，更改元素值并进一步递归
            if (check(i, y)) {
                Queen8.chessBoard[i][y] = 1;
                // 递归如果返回True，说明下层已找到解法，无需继续循环
                if (settleQueen(y + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 打印棋盘当前值
     */
    private void printChessBoard() {
        for (int i = 0; i < Queen8.MAX_NUM; i++) {
            for (int j = 0; j < Queen8.MAX_NUM; j++) {
                System.out.print(Queen8.chessBoard[j][i]);
            }
            System.out.println();
        }
    }

}
