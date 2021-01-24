package com.example.lottery;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import static java.lang.Math.ceil;

public class Balls {
    private static final int EJP = 1;
    // private static final int LOTTO = 2;  // not currently used

    private static final int EJP_TOTAL = 50;
    private static final int EJP_BALLS = 5;
    private static final int EJP_STARS = 2;
    private static final int EJP_STARS_RANGE = 10;
    private static final int L_TOTAL = 40;
    private static final int L_BALLS = 7;
    private static final int L_PLUS = 1;
    private static final int L_PLUS_RANGE = 30;

    /**
     * Initialize the given list with its indices
     */
    private void listInit(LinkedList<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }

    /**
     * Draw all balls in a random order so that we get every ball
     * (at least) once.
     */
    public LinkedList<Integer> draw(int game) {
        final int total = game == EJP ? EJP_TOTAL : L_TOTAL;
        final int balls = game == EJP ? EJP_BALLS : L_BALLS;
        final int rows = (int) (game == EJP
                        ? ceil((double) EJP_TOTAL / EJP_BALLS)
                        : ceil((double) L_TOTAL / L_BALLS));
        final int extra = game == EJP ? EJP_STARS : L_PLUS;
        final int extraRange = game == EJP ? EJP_STARS_RANGE : L_PLUS_RANGE;

        LinkedList<Integer> listAll = new LinkedList<>();
        LinkedList<Integer> result = new LinkedList<>();
        Random r = new Random();

        listInit(listAll, total);

        for (int i = 0; i < rows; i++) {
            LinkedList<Integer> line = new LinkedList<>();

            for (int j = 0; j < balls && listAll.size() > 0; j++) {
                int ball = r.nextInt(listAll.size());

                line.add(listAll.remove(ball));
            }

            // If an additional row (as in Lotto) is needed to get
            // the remaining unselected numbers included, we have to
            // create a line containing those plus some that will
            // occur two times
            if (i == rows - 1 && line.size() < balls) {

                // Reinitialize listAll
                for (int k = 0; k < total; k++) {
                    listAll.add(k);
                }

                // Remove those already in line
                for (int k = 0; k < line.size(); k++) {
                    int ball = line.get(k);

                    // Better than listAll.remove(listAll.indexOf(ball)); ?
                    listAll.remove((Integer) ball);
                }

                // Now complete line
                while (line.size() < balls) {
                    int ball = r.nextInt(listAll.size());

                    line.add(listAll.remove(ball));
                }
            }

            Collections.sort(line);

            // Get extra balls
            LinkedList<Integer> listExtra = new LinkedList<>();

            listInit(listExtra, extraRange);

            for (int j = 0; j < extra; j++) {
                int ball = r.nextInt(listExtra.size());

                line.add(listExtra.remove(ball));
            }

            // Sort the two extra balls if Eurojackpot
            if (game == EJP && line.get(5) > line.get(6)) {
                int first = line.get(5);

                line.set(5, line.get(6));
                line.set(6, first);
            }

            // Make the resulting ball numbers one-based
            for (int j = 0; j < line.size(); j++) {
                result.add(line.get(j) + 1);
            }
        }
        return result;
    }
}
