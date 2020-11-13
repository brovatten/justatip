package com.classes.tip;

public class Taxi {
    private boolean interval;
    private double low_tip_percent;
    private double high_tip_percent;

    public double getHigh_tip_percent() {
        return high_tip_percent;
    }

    public void setHigh_tip_percent(double high_tip_percent) {
        this.high_tip_percent = high_tip_percent;
    }

    public Taxi(double low_tip_percent, double high_tip_percent) {
        this.low_tip_percent = low_tip_percent;
        this.high_tip_percent = high_tip_percent;
        this.interval = true;
    }

    public Taxi(double high_tip_percent) {
        this.high_tip_percent = high_tip_percent;
    }

    public double[] calculate_tip(double sum) {
        if (interval) {
            double low_end;
            low_end = low_tip_percent * sum;
            low_end = Math.round(low_end * 1000d) / 1000d;

            //Mathround avrundar bort alla decimaler. Multipliera
            //därför med 1000 för o få avrundning till 3 decimaler
            double high_end = sum * high_tip_percent;
            high_end = Math.round(high_end * 1000.0) / 1000.0;
            return new double[]{low_end, high_end};
        } else {
            if (high_tip_percent == 0)
                return new double[]{0};
            else {
                double high_end = sum * high_tip_percent;
                high_end = Math.round(high_end * 1000.0) / 1000.0;
                return new double[]{high_end};
            }
        }
    }
}
