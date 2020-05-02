package comp1110.ass2;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class VerifyPlacementTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    private void test1(String piecePlacement, boolean expect){
        boolean verifyPlacementNotOverlapresult = VerifyPlacement.isPlacementNotOverlap(piecePlacement);
        Assert.assertTrue("Expect " + expect + " but got " + verifyPlacementNotOverlapresult,
                expect==verifyPlacementNotOverlapresult);
    }

    @Test
    public void testNotOverlap(){
        test1("aaaa00aaaa77", true);
        test1("aaaa00bbbb77", true);
        test1("aaaa00aacb01caba02adad10adbb22baac23bbad32dddd77", true);
    }

    @Test
    public void testOverlap(){
        test1("aaaa00aaaa00", false);
        test1("aaaa00bbbb00", false);
        test1("aaaa77bbbb77", false);
        test1("aaaa00aacb01caba02adad10adbb22baac23bbad32dddd00", false);
    }


    private void test2(String piecePlacement, boolean expect){
        boolean verifyPlacementInTheBoundresult = VerifyPlacement.isPlacementInTheBound(piecePlacement);
        Assert.assertTrue("Expect " + expect + " but got " + verifyPlacementInTheBoundresult,
                expect==verifyPlacementInTheBoundresult);
    }

    @Test
    public void testOutOfBound(){
        int a=(int)(Math.random()*100);
        String b= ""+a;
        if (a>=0&&a<10) b="0"+"b";
        if (a < 0 || a >77 || a==33 || a==44) {
            test2( "aaaa"+b, false);
        }else if (a==33 || a==34 || a == 43 || a==44){
            test2( "bbbb"+a, false);
        }
        test2( "aaaa00bbbb78", false);
        test2( "aaaa33aaaa77", false);
    }

    @Test
    public void testNotOutOfBound(){
        int a=(int)(Math.random()*100);
        if (a >= 0 && a <=77 && a!=33 && a!=34 && a!=43 && a!=44) {
            test2( "aaaa"+a, true);
        }
        test2( "aaaa00aacb01acba37bbbb77", true);
    }



    private void test3(String piecePlacement, boolean expect){
        boolean isPlacementSequenceWellFormedresult = Metro.isPlacementSequenceWellFormed(piecePlacement);
        Assert.assertTrue("Expect " + expect + " but got " + isPlacementSequenceWellFormedresult,
                expect==isPlacementSequenceWellFormedresult);
    }

    @Test
    public void testRightString(){
        test3( "aaaa00dddd77", true);
        test3( "aaaa00aaaa01aaaa02aaaa03dddd67dddd77", true);
    }

    @Test
    public void testWrongString(){
        test3( "aaaa00aaaa11aaaa22aaaa45aaaa77", false);
        test3( "dddd00dddd11dddd77", false);
        test3( "aaaa00dddd00dddd11aaa77", false);
    }

}
