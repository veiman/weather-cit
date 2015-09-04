package ru.veiman.provider.impl.owm.dto;

/**
 * Created by veiman on 21.08.15.
 */
public class WindDTO {
    private int speed;
    private int deg;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WindDTO{");
        sb.append("speed=").append(speed);
        sb.append(", deg=").append(deg);
        sb.append('}');
        return sb.toString();
    }
}
