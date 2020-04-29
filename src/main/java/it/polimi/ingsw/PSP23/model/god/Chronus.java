package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Worker;

public class Chronus extends God {
    private int completed_tower;

    public Chronus() {
        this.setUpGod("Chronus");
    }

    @Override
    protected void setUpGod(String godName) {
        super.setUpGod(godName);
        this.completed_tower = 0;
    }

    @Override
    public void startTurn(boolean moved_up) {
        super.startTurn(moved_up);
        this.checkWin(null);
    }

    @Override
    public boolean checkWin(Worker w) {
        if ((null != w) && (super.checkWin(w)))
            return true;
        else
            return (5 <= this.completed_tower);
    }

    public void addCompletedTower() {
        this.completed_tower++;
    }
}
