class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int a1 = 0, a2 = 0, b1 = 0, b2 = 0;

        int m = Integer.parseInt(arriveAlice.substring(0, 2));
        int d = Integer.parseInt(arriveAlice.substring(3, 5));
        for (int i = 0; i < m - 1; i++) {
            d += days[i];
        }
        a1 = d;

        m = Integer.parseInt(leaveAlice.substring(0, 2));
        d = Integer.parseInt(leaveAlice.substring(3, 5));
        for (int i = 0; i < m - 1; i++) {
            d += days[i];
        }
        a2 = d;

        m = Integer.parseInt(arriveBob.substring(0, 2));
        d = Integer.parseInt(arriveBob.substring(3, 5));
        for (int i = 0; i < m - 1; i++) {
            d += days[i];
        }
        b1 = d;

        m = Integer.parseInt(leaveBob.substring(0, 2));
        d = Integer.parseInt(leaveBob.substring(3, 5));
        for (int i = 0; i < m - 1; i++) {
            d += days[i];
        }
        b2 = d;

        int start;
        if (a1 > b1) {
            start = a1;
        } else {
            start = b1;
        }

        int end;
        if (a2 < b2) {
            end = a2;
        } else {
            end = b2;
        }

        if (start > end) {
            return 0;
        } else {
            return end - start + 1;
        }
    }
}
