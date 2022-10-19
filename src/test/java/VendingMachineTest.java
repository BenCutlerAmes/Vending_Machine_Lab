import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class VendingMachineTest {

    VendingMachine vendingMachine;

    @Before
    public void before(){
        vendingMachine = new VendingMachine(10,10,10,10,10,10);

    }

    @Test
    public void canAddCoin(){
        vendingMachine.addCoin(Coin.FIVE);
        assertEquals(1,vendingMachine.getCoinHopper().size());
        assertEquals(0, vendingMachine.getCoinReturn().size());

    }

    @Test
    public void canRejectCoppers() {
        vendingMachine.addCoin(Coin.ONE);
        assertEquals(0,vendingMachine.getCoinHopper().size());
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
        assertEquals(0,vendingMachine.getCoinHopper().size());
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
        assertEquals(13,vendingMachine.getFivePenceCount());
        assertEquals(10,vendingMachine.getTenPenceCount());
        assertEquals(10,vendingMachine.getTwentyPenceCount());
        assertEquals(12,vendingMachine.getFiftyPenceCount());
        assertEquals(11,vendingMachine.getOnePoundCount());
        assertEquals(10,vendingMachine.getTwoPoundCount());
    }

    @Test
    public void canGetFloatTotal() {
        assertEquals(3850,vendingMachine.getFloatTotal());
    }
}
