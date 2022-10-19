import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VendingMachine {

    private int fivePenceCount;
    private int tenPenceCount;
    private int twentyPenceCount;
    private int fiftyPenceCount;
    private int onePoundCount;
    private int twoPoundCount;

    private ArrayList<Coin> coinHopper;
    private ArrayList<Coin> coinReturn;
    private ArrayList<Product> stock;

    public VendingMachine(int fivePenceCount, int tenPenceCount, int twentyPenceCount, int fiftyPenceCount, int onePoundCount, int twoPoundCount) {
        this.fivePenceCount = fivePenceCount;
        this.tenPenceCount = tenPenceCount;
        this.twentyPenceCount = twentyPenceCount;
        this.fiftyPenceCount = fiftyPenceCount;
        this.onePoundCount = onePoundCount;
        this.twoPoundCount = twoPoundCount;
        this.coinHopper = new ArrayList<>();
        this.stock = new ArrayList<>();
        this.coinReturn = new ArrayList<>();
    }

    public int getFivePenceCount() {
        return fivePenceCount;
    }

    public int getTenPenceCount() {
        return tenPenceCount;
    }

    public int getTwentyPenceCount() {
        return twentyPenceCount;
    }

    public int getFiftyPenceCount() {
        return fiftyPenceCount;
    }

    public int getOnePoundCount() {
        return onePoundCount;
    }

    public int getTwoPoundCount() {
        return twoPoundCount;
    }

    public ArrayList<Coin> getCoinHopper() {
        return coinHopper;
    }

    public ArrayList<Product> getStock() {
        return stock;
    }

    public ArrayList<Coin> getCoinReturn() {
        return coinReturn;
    }

    public void addCoin(@NotNull Coin coin) {
        if (coin.getValue() == 1 || coin.getValue() == 2) {
            this.coinReturn.add(coin);
        } else {
            this.coinHopper.add(coin);
        }
    }

    public void cancelTransaction() {
        for (Coin coin : this.coinHopper) {
            this.coinReturn.add(coin);

        }
        this.coinHopper.clear();
    }

    public void processCoins() {
        for (Coin coin : this.coinHopper) {
            if (coin == Coin.FIVE) {
                this.fivePenceCount += 1;
            } else if (coin == Coin.TEN) {
                this.tenPenceCount += 1;
            } else if (coin == Coin.TWENTY) {
                this.twentyPenceCount += 1;
            } else if (coin == Coin.FIFTY) {
                this.fiftyPenceCount += 1;
            } else if (coin == Coin.ONE_POUND) {
                this.onePoundCount += 1;
            } else if (coin == Coin.TWO_POUND) {
                this.twoPoundCount += 1;
            }
        }
    }

    public int getFloatTotal() {
        int floatTotal = 0;
        return fivePenceCount * 5 + tenPenceCount * 10 + twentyPenceCount * 20 + fiftyPenceCount * 50 + onePoundCount * 100 + twoPoundCount * 200;
    }
}
