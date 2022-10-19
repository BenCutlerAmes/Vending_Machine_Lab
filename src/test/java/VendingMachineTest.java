import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class VendingMachineTest {

    VendingMachine vendingMachine;
    Product crisps;
    Product coke;
    Product sweets;

    @Before
    public void before() {
        vendingMachine = new VendingMachine(10, 10, 10, 10, 10, 10);
        crisps = new Product("Crisps", 50);
        coke = new Product("Coke", 100);
        sweets = new Product("Sweets", 65);
        vendingMachine.addStock(coke);
        vendingMachine.addStock(sweets);
        vendingMachine.addStock(crisps);
    }

    @Test
    public void canAddCoin() {
        vendingMachine.addCoin(Coin.FIVE);
        assertEquals(1, vendingMachine.getCoinHopper().size());
        assertEquals(0, vendingMachine.getCoinReturn().size());

    }

    @Test
    public void canRejectCoppers() {
        vendingMachine.addCoin(Coin.ONE);
        assertEquals(0, vendingMachine.getCoinHopper().size());
        assertEquals(1, vendingMachine.getCoinReturn().size());

    }

    @Test
    public void canCancelTransaction() {
        vendingMachine.addCoin(Coin.FIVE);
        vendingMachine.addCoin(Coin.FIVE);
        vendingMachine.addCoin(Coin.FIVE);
        vendingMachine.addCoin(Coin.FIVE);
        vendingMachine.addCoin(Coin.FIVE);
        vendingMachine.addCoin(Coin.FIVE);
        vendingMachine.addCoin(Coin.FIVE);
        vendingMachine.cancelTransaction();
        assertEquals(0, vendingMachine.getCoinHopper().size());
        assertEquals(7, vendingMachine.getCoinReturn().size());
    }

    @Test
    public void canProcessCoins() {
        vendingMachine.addCoin(Coin.FIVE);
        vendingMachine.addCoin(Coin.FIVE);
        vendingMachine.addCoin(Coin.ONE);
        vendingMachine.addCoin(Coin.FIFTY);
        vendingMachine.addCoin(Coin.FIFTY);
        vendingMachine.addCoin(Coin.FIVE);
        vendingMachine.addCoin(Coin.ONE_POUND);
        vendingMachine.processCoins();
        assertEquals(13, vendingMachine.getFivePenceCount());
        assertEquals(10, vendingMachine.getTenPenceCount());
        assertEquals(10, vendingMachine.getTwentyPenceCount());
        assertEquals(12, vendingMachine.getFiftyPenceCount());
        assertEquals(11, vendingMachine.getOnePoundCount());
        assertEquals(10, vendingMachine.getTwoPoundCount());
    }

    @Test
    public void canGetFloatTotal() {
        assertEquals(3850, vendingMachine.getFloatTotal());
    }

    @Test
    public void canBuyExactMoney() {
        vendingMachine.addCoin(Coin.ONE_POUND);
        vendingMachine.buy(coke);
        assertEquals(0, vendingMachine.getCoinHopper().size());
        assertEquals(11, vendingMachine.getOnePoundCount());

    }

    @Test
    public void canGetHopperTotal() {
        vendingMachine.addCoin(Coin.FIFTY);
        vendingMachine.addCoin(Coin.ONE_POUND);
        assertEquals(150, vendingMachine.getHopperTotal());
    }

    @Test
    public void canAfford() {
        vendingMachine.addCoin(Coin.FIFTY);
        vendingMachine.addCoin(Coin.TWENTY);
        vendingMachine.buy(sweets);
        assertEquals(11,vendingMachine.getFiftyPenceCount());
        assertEquals(0, vendingMachine.getHopperTotal());
    }

    @Test
    public void cantAfford() {
        vendingMachine.addCoin(Coin.FIFTY);
        vendingMachine.addCoin(Coin.TWENTY);
        vendingMachine.buy(coke);
        assertEquals(70, vendingMachine.getHopperTotal());
    }

    @Test
    public void canGetCoinsNeeded(){
        assertEquals(3,vendingMachine.getCoinsNeeded(65).size());
    }

    @Test
    public void canGiveChange(){
        vendingMachine.addCoin(Coin.TWO_POUND);
        vendingMachine.buy(sweets);
        assertEquals(4,vendingMachine.getCoinReturn().size());
    }


}
